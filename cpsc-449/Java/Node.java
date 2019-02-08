import java.util.ArrayList;

//represents the nodes for the branch and bound tree

public class Node {
	//instance variables
	private int penalty;
	private int lowerBound;
	private int machine;
	private int task;
	private Node parent;
	private ArrayList<Node> children;
	

	
	//constructors
	public Node(int m, int t, Node n, int[][] mP, TooNearPenalty tNPSoft, ArrayList<Integer> aT) {
		this.machine = m;
		this.task = t;
		this.parent = n;
		this.penalty = this.calcPenalty(mP, tNPSoft);
		this.lowerBound = this.calcLowerBound(mP, aT);
		this.children = new ArrayList<Node>(3 - m);
	}
	
	public Node(int[][] mP, ArrayList<Integer> aT) {
		this.machine = -1;
		this.task = -1;
		this.parent = null;
		this.penalty = 0;
		this.lowerBound = this.calcLowerBound(mP, aT);
		this.children = new ArrayList<Node>(8);
	}

	//getters
	public int getPenalty() {
		return this.penalty;
	}
	public int getLowerBound() {
		return this.lowerBound;
	}
	
	public int getMachine() {
		return this.machine;
	}
	
	public int getTask() {
		return this.task;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public ArrayList<Node> getChildren() {
		return this.children;
	}
	
	//methods
	public int calcPenalty(int[][] machinePenalties, TooNearPenalty tNPSoft) {
		//variables to facilitate calculation of new penalty
		int prevPenalty, marginalPenalty, tooNearSoftPenalty;
		//get lower bound of parent or, if root, set to 0
		if (this.parent != null) {
			prevPenalty = this.parent.getPenalty();
		} else {
			prevPenalty = 0;
		}
		//calculate the penalty for this node's task assignment from machine penalty 
		//array
		marginalPenalty = machinePenalties[this.machine][this.task];
		Node parent = this.parent;
		tooNearSoftPenalty = 0;
		
		//while loop for too near soft penalty calc to find task and nextTask
		while (parent != null) {
			if(parent.machine == this.machine + 1) {
				tooNearSoftPenalty += tNPSoft.tooNearSoftPenalty(this.task, parent.task);
			}
			else if(this.machine == parent.machine +1) {
				tooNearSoftPenalty += tNPSoft.tooNearSoftPenalty(parent.task, this.task);
			}
			
			parent = parent.parent;
		}
		
		return(prevPenalty + marginalPenalty + tooNearSoftPenalty);
	}
	
	private int findMinTask(int[] taskPenalties, ArrayList<Integer> availableTasks) {
		int minTaskPenalty = Integer.MAX_VALUE;
		for(Integer i : availableTasks) {
			if(i.equals(this.task)) {
				continue;
			}
			if(taskPenalties[i] < minTaskPenalty) {
				minTaskPenalty = taskPenalties[i];
			}
		}
		return minTaskPenalty;
	}
	
	public int calcLowerBound(int[][] machinePenalties, ArrayList<Integer> availableTasks) {
		int lowerBound = this.penalty;
		for(int i = this.machine + 1; i < 4; i++) {
			lowerBound += this.findMinTask(machinePenalties[i], availableTasks);
		}
		return lowerBound;
	}
	//m=machine t=task mP=machineP at=availTask
	public void addChild(int m, int t, int[][] mP, TooNearPenalty tNPSoft, ArrayList<Integer> aT) {
		//create a node with given parameters and with this node as parent
		Node child = new Node(m, t, this, mP, tNPSoft, aT);//this = whatever node addchild was called on
		//add new node as child
		this.children.add(child);//adds to arrayList of nodes
	}
/*	
	public static void main(String[] args) {
		int[][] machinePenalties = {{9, 2, 7, 8}, {6, 4, 3, 7}, {5, 8, 1, 8}, {7, 6, 9, 4}};
		ArrayList<Integer> availableTasks = new ArrayList<>(8);
		for(int i = 0; i < 4; i++) {
			availableTasks.add(i);
		}
		
		System.out.println(availableTasks);
		
		System.out.println("");
		
		Node root = new Node(machinePenalties, availableTasks);
		Node currentNode = root;
		currentNode.addChild(0, 1, machinePenalties, availableTasks);
		currentNode = currentNode.children.get(0);
		
		
		System.out.println(root.machine);
		System.out.println(root.task);
		System.out.println(root.penalty);
		System.out.println(root.lowerBound);
		System.out.println(root.parent);
		System.out.println(root.children);
		
		System.out.println("");
		
		System.out.println(currentNode.machine);
		System.out.println(currentNode.task);
		System.out.println(currentNode.lowerBound);
		System.out.println(currentNode.parent);
		System.out.println(currentNode.children);
	}
*/
}
