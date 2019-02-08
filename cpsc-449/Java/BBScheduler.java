import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


//class which represents the schedule builder which assigns the tasks
//to the machines to determine the optimal solution to the problem

public class BBScheduler {
	//instance variables
	private int[][] machinePenalties;
	private ArrayList<Integer> availableTasks;
	private ArrayList<Integer> keepAvailTasks;
	private int proposedTask;
	private int[] solution;
	private Node currentNode;
	private TooNearPenalty tNPsoft;
	
	
	public int[] getSolution() {
		return solution;
	}
	//constructor: accepts machine Penalties, Too near penalties from file input
	public BBScheduler(int[][] mP, ArrayList<ArrayList<Integer>> tooNearPenalties) {
		this.machinePenalties = mP;
		this.availableTasks = new ArrayList<>(8);
		this.keepAvailTasks = new ArrayList<>(8);
		for(int i = 0; i < 4; i++) {
			availableTasks.add(i);
			keepAvailTasks.add(i);
		}
		this.tNPsoft = new TooNearPenalty(tooNearPenalties);
	}
	
	//methods
	public void proposeNextAssignment() {/////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(Integer i : this.availableTasks) {
			this.currentNode.addChild(this.currentNode.getMachine() + 1, i, this.machinePenalties, this.tNPsoft, this.availableTasks);
		}
	}
	
	private Node findBestNode() {
		int lowestBound = Integer.MAX_VALUE;
		Node nextNode = null;
		for(Node n : this.currentNode.getChildren()) {
			if(n.getLowerBound() < lowestBound) {
				lowestBound = n.getLowerBound();
				nextNode = n;
			}
		}
		return nextNode;
	}
	
	public void makeNextAssignment() {
		int indexOfTask;
		this.currentNode = this.findBestNode();
		indexOfTask = this.availableTasks.indexOf(this.currentNode.getTask());
		this.availableTasks.remove(indexOfTask);
	}
	
	public void generateSolution() {
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// this only allows one pass through one tree for best option. We should have an
		/////////////////////////////////////////////////////////////////////////////////////////////////// array/?stack
		/////////////////////////////////////////////////////////////////////////////////////////////////// that
		// starts tree at M0, then when done, start at M1, etc till M8
//trying to make an array, then remove index 0, and add it to the end. like a queue. Keep gettin index out of bound
		//at line 100
		//just implement a queue and insert front one to back. To stop after all machine bests have been found
		Queue<Integer> availMachines = new LinkedList<>();
		for(int i=0; i<4; i++) {//population of 4 machines
			availMachines.add(i);
			
		}
		System.out.println("Available Machines: "+ availMachines);
		//place below 3 lines at end of while loop to make best selection tree for each machine start.
		//
		availMachines.remove();//remove head
		Queue<Integer> remainMachines =  availMachines;
		System.out.println("Remaining Machines: " + remainMachines);
///////////////////////////////////////////////
			while (availMachines.size()>0) {
				int head = availMachines.peek();
////////////////////////////////////////////////////////
//now I have a machine number where I now need to get into current node.
//then propose next assignment, and perhaps skip line 87 while loop.
				//////////////////////////////////////////////
				//////////////////////////////////////////////
				//////////////////////////////////////////////
			}
			while (this.currentNode.getMachine() < 3) {
				// System.out.println("machine: " + this.currentNode.getMachine() + ", task: " +
				// this.currentNode.getTask() + ", lower bound: " +
				// this.currentNode.getLowerBound());
				// System.out.println("");
				this.proposeNextAssignment();
				for (Node n : this.currentNode.getChildren()) {
					System.out.println("machine: " + n.getMachine() + ", task: " + n.getTask() + ", lower bound: "
							+ n.getLowerBound());
				}
				System.out.println();
				this.makeNextAssignment();
			}
			System.out.println("machine: " + this.currentNode.getMachine() + ", task: " + this.currentNode.getTask()
					+ ", lower bound: " + this.currentNode.getLowerBound());
			// assuming we are at the farthest node of the solution we can walk back and
			// read off machine numbers to get the solution in reversed order
			do {
				System.out.print(this.currentNode.getMachine() + ":" + this.currentNode.getTask() + ", ");
				this.currentNode = this.currentNode.getParent();
			} while (this.currentNode.getParent() != null);

	}
	public static void main(String[] args) {
		int[][] machinePenalties = {{9, 2, 7, 8}, {6, 4, 3, 7}, {5, 8, 1, 8}, {7, 6, 9, 4}};

		int[][] machinePenaltiesALT = {{9, 1, 5, 8}, {6, 0, 3, 7}, {2, 8, 4, 8}, {7, 6, 9, 4}}; 

		//for TooNearPenalty Soft
		ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>( 
	            Arrays.asList( 
	            		new ArrayList<Integer>(Arrays.asList(1,2,300))//P=300
	  //          		new ArrayList<Integer>(Arrays.asList(1,3,2)),
	  //          		new ArrayList<Integer>(Arrays.asList(5,3,2))
	            		)); 
		
		BBScheduler scheduler = new BBScheduler(machinePenaltiesALT, a);
		scheduler.currentNode = new Node(scheduler.machinePenalties, scheduler.availableTasks);
		scheduler.generateSolution();
		/*for(int i: scheduler.getSolution()) {
			System.out.print(i+" ");
		}
		System.out.println();*/

		

	}

}
