import java.util.ArrayList;
import java.util.Arrays;

public class TooNearPenalty {
	
	ArrayList<ArrayList<Integer>> tooNearPenalty = new ArrayList<ArrayList<Integer>>();
	/*
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>( 
		            Arrays.asList(
		            		new ArrayList<Integer>(Arrays.asList(1,2,3)),
		            		new ArrayList<Integer>(Arrays.asList(1,3,2)),
		            		new ArrayList<Integer>(Arrays.asList(5,3,2))
		            		)); 
		TooNearPenalty tnu = new TooNearPenalty(a);
//		tnu.setTooNearTasks(a);
		
		System.out.println(tnu.tooNearSoftPenalty(5,3));
	}
	*/
	//constructor
	public TooNearPenalty(ArrayList<ArrayList<Integer>> arrayOf8with3elements) {
		this.tooNearPenalty = arrayOf8with3elements;
	}
	/*
	//set by the parser
	public void setTooNearTasks(ArrayList<ArrayList<Integer>> arrayof8with3ele) {
		this.tooNearPenalty = arrayof8with3ele;
	} 
		//done by parser
	public ArrayList<ArrayList<Integer>> getTooNearTasks() {
		return tooNearPenalty;
	}
	*/
	
	public Integer tooNearSoftPenalty(int currTask, int nextTask) {

		// assuming input of the form (currentTask, int nextTask for machine i+1) when
		// method called

		int task = currTask;

		int taskNext = nextTask;

		// getting array set by parser tnp = tooNearPenalty
		ArrayList<ArrayList<Integer>> tnp = tooNearPenalty;
		int penalty = 0;

		// going through tnp to find matches of tasks
		for (int i = 0; i < tnp.size(); i++) {
			int j = 0;
			ArrayList<Integer> cTriple = tnp.get(i); // maybe just AL?
			int cTask = cTriple.get(j); // cTask is the first task in the triple to be compared with CT
			if (currTask == cTriple.get(0) && nextTask == cTriple.get(1)) {
				return cTriple.get(2);
			}

		}
		return penalty;
	}
}
