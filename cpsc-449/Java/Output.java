import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


/*
 * As the program runs methods that print out individual lines should be called.
 * WRITER MUST BE CLOSED BEFORE PROGRAM TERMINATES 
 */
public class Output {
	
	private  static String filename = "myoutput";
	private static File file;
	private static PrintWriter writer;


	/**
	 * constructor to initialize writing to file
	 * @param filename name of the output file (do not add .txt)
	 */
	public Output (String filename){
		createFile(filename);
	}


	
	public static void main(String[] args) {
		createFile("myoutput");
		/*
		parserError();
		invalidMachineTask();
		invalidPenalty();
		invalidTask();
		machinePenaltyError();
		parserError();
		noSolution();
		partialAssignmentError();
		*/
		
		//testing writing solution to file
		char[] a;
		a = new char[8];
		a[0] = 'B';
		a[1] = 'A';
		a[2] = 'C';
		a[3] = 'F';
		a[4] = 'D';
		a[5] = 'E';
		a[6] = 'G';
		a[7] = 'H';
		soultion(a, 20);
		
		
		//Testing find duplicate
		char[][] b = new char[][] {{'1','A'},{'2','B'},{'3','C'}};
		char[][] c = new char[][] {{'1','A'},{'1','B'},{'3','C'}};
		char[][] d = new char[][] {{'1','A'},{'2','A'},{'3','C'}};
		boolean q;
		boolean r;
		boolean s;
		q = findDuplicate(b);
		r = findDuplicate(c);
		s = findDuplicate(d);
		System.out.println("is: " + q + " " + r + " " + s + " should be: false true true");
		
		
		//testing out tooNearError
		char[][] e = new char[][] {{'A','B'},{'C','D'},{'E','H'}};
		char[][] f = new char[][] {{'A','B'},{'C','D'},{'E','H'},{'I','J'}};
		q= tooNearError(e);
		r = tooNearError(f);
		System.out.println("is: " + q + " " + r + " should be: false true");
		
		
		//testing out checkInts
		int[][] g = new int[][] {{1,2,3,4,5,6,7,8},{1,2,3,4,5,6,7,8},{1,2,3,4,5,6,7,8},{1,2,3,4,5,6,7,8},
		{1,2,3,4,5,6,7,8},{11,12,13,14,15,16,17,18},{1,2,3,4,5,6,7,8},{111,112,113,114,115,116,117,118}};
		
		int[][] h = new int[][] {{1,2,3,4,5,6,7,8},{1,2,3,4,5,6,7,8},{1,2,3,4,5,6,7,8},{1,2,3,4,5,6,7,8},
		{1,2,3,4,5,6,7,8},{11,12,13,14,15,16,17,18},{1,-2,3,4,5,6,7,8},{111,112,113,114,115,116,117,118}};	
		
	
		q = checkInts(g);
		r = checkInts(h);
		System.out.println("is: " + q + " " + r + " should be: false true");
	
	}
		
	
	
	
	/**creates a file with a given name, creates a new one if it already exists
	 * initiates a writer for the file
	 * 
	 * @param name name of file (don't add .txt)
	 */
	public static void createFile(String name) {
		filename = name + ".txt";
		file = new File(filename);
        if(file.delete()){
            System.out.println(filename + " file deleted");
        	}
        else {
        	System.out.println(filename + " doesn't exists");
			}
		try {
			if(file.createNewFile()) {
				System.out.println("File is created!");
			}
			else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * writes "partial assignment error"
	 */
	public static void partialAssignmentError() {
		writer.println("partial assignment error");
		closeWriter();
	}
	
	/**
	 * writes "invalid machine/task"
	 */
	public static void invalidMachineTask() {
		writer.println("invalid machine/task");
		closeWriter();
	}
	
	/**
	 * writes "machine penalty error"
	 */
	public static void machinePenaltyError() {
		writer.println("machine penalty error");
		closeWriter();
	}
	
	/**
	 * writes "invalid task"
	 */
	public static void invalidTask() {
		writer.println("invalid task");
		closeWriter();
	}
	
	/**
	 * writes invalid penalty
	 */
	public static void invalidPenalty() {
		writer.println("invalid penalty");
		closeWriter();
	}
	
	/**
	 * writes "Error while parsing input file" if no other message written before this one
	 */
	public static void parserError() {
		writer.println("Error while parsing input file");
		closeWriter();
	}
	
	/**
	 * writes "No valid solution possible!"
	 */
	public static void noSolution() {
		writer.println("No valid solution possible!");
		closeWriter();
	}
	
	/**prints out the solution
	 * 
	 * @param solutions solution array
	 * @param penalty penalty
	 */
	public static void soultion(char[] solutions, int penalty) {
		String string = "Solution ";
		string += solutions[0] + " ";
		string += solutions[1] + " ";
		string += solutions[2] + " ";
		string += solutions[3] + " ";
		string += solutions[4] + " ";
		string += solutions[5] + " ";
		string += solutions[6] + " ";
		string += solutions[7];
		string += "; Quality:";
		string = string + penalty;
		writer.println(string);
		closeWriter();
	}
	
	
	//closes writer
	public static void closeWriter() {
		writer.close();
	}
	
	/**checks if list contains a duplicate task or machine,
	*@param a 2d array of machine, task
	*@return true if a duplicate is found
	*/ 	
	private static boolean findDuplicate(char[][] a) {
		boolean duplicate = false;
		if (a.length >= 2) {
			int i=0;
			char[] b = new char[a.length];
			while(!duplicate && i < a.length ) {
				b[i] = a[i][0];
				if (i > 0) {
					int x = i - 1;
					while (x >= 0) {
						if(b[i] == b[x]) {
							duplicate = true;
						}
						x -= 1;
					}
				}
				i += 1;
			}
			if (!duplicate) {
				i=0;
				char[] c = new char[a.length];
				while(!duplicate && i < a.length ) {
					c[i] = a[i][1];
					if (i > 0) {
						int x = i - 1;
						while (x >= 0) {
							if(c[i] == c[x]) {
								duplicate = true;
							}
							x -= 1;
						}
					}
					i += 1;
				}
			}
		}
		
		return duplicate;
		
	}
	
	
	//Not Correct
	//Since elements in array cannot be null, (automatically 0) testing to see if the 
	//penalties are organized 8 by 8 cannot be done, should be done by  
	//parser or in Arraylist
	/**
	 * Algorithm to see that it is 8 by 8
	 * @param a 2d array of ints
	 * @return true if it is not 8 by 8
	 */
	private static boolean penError(int[][] a) {
		boolean error = false;
		int i = 0;
		while(i < 8 && !error) {
			if (a[i][7] == 0) {
				error = true;
			}
		}
		return error;
	}

	/**
	 * Makes sure tasks are between A and H
	 * @param a 2d array of chars containing tasks
	 * @return return true if invalid task is found
	 */
	private static boolean tooNearError(char[][] a) {
		boolean error = false;
		int i = 0;
		while(i < a.length && !error) {
			if (a[i][0] >= 'A' && a[i][0] <= 'H') {
				if (a[i][1] >= 'A' && a[i][1] <= 'H'){
				}
				else {
					error = true;
				}
			}
			else {
				error = true;
			}
			i++;
		}
		return error;
	}
	
	
	/**
	 * makes sure ints are non negative (for machine penalty soft constraint should be 8 by 8
	 * but will work for any 2d array of ints)
	 * @param a 2d array of ints
	 * @return true if negative number found
	 */
	private static boolean checkInts(int[][] a) {
		boolean error = false;
		int i = 0;
		int j = 0;
		while( i < a.length && ! error) {
			j = 0;
			while(j < a[i].length && !error) {
				if(a[i][j] < 0) {
					error = true;
				}
				j++;
			}
			i++;
		}
		
		return error;
	}
	
	/**
	 * make sure ints are non- negative (for too near penalties ie [x][3] where last element in 
	 * each subarray is an int)
	 * PROBLEM WITH THIS ALGORITHM: Since this pair contains numbers and chars and the numbers can 
	 * greater than 9 it cannot be checked properly. 
	 * @param a 2d array of ints 
	 * @return true if negative number found
	 */
	public boolean checkPens(char[][] a) {
		boolean error = false;
		int i = 0;
		while( i < a.length) {
			if(a[i][2] < '0') {
				error = true;
			}
			i++;
		}
		return error;
	}
	
	
	
	/**
	public static void output(boolean parseError, boolean solutionFound) {
		Output out = new Output();
		out.createFile(Parser.getOutputFile());
		
		char[][] a = Parser.getforcedPartialAssignment();
		if (out.findDuplicate(a)) {
			out.partialAssignmentError();
		}
		if(forbiddenMachine() || tooNearTaskConstraint() || forcedPartialContraint() ) {
			out.invalidMachineTask();
		}
		
		int [][] b = Parser.getMachinePenalties();
		if (out.penError(b)) {
			out.machinePenaltyError();
		}
		char[][] c = Parser.getTooNearPenalties();
		if (out.tooNearError(c)) {
			out.invalidTask();
		}
		
		if(out.checkInts(b)|| out.checkPens(c)) {
			out.invalidPenalty();
		}
		
		if(parseError) {
			out.parserError();
		}
		
		
		if(solutionFound) {
			char[] solved = Parser.getSolutions();
			out.soultion(solved, Parser.getPenalty());
		}
		else {
			out.noSolution();
		}
		
		out.closeWriter();
		
		
		
	}
	*/

	

}
