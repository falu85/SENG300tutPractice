import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Scratch {
	int[][] machinePenalties;
	
	public Scratch(int[][] mp) {
		this.machinePenalties = mp;
	}
	
    private int calcMachinePenalty(Character[] copy, int index) {
        //method to calculate the penalty for assigning the task at index of copy
        //to machine represented by index
        //convert task from char to int value for array indexing
        int task = Character.getNumericValue(copy[index]);
        task = task - 10;
        //retrieve machine penalty using index for machine and task for task
        int machinePenalty = this.machinePenalties[index][task];
        return machinePenalty;
    }
    
    private int[][] newMPArray(Random rng) {
    	int[][] machinePenalties = new int[8][8];
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			machinePenalties[i][j] = rng.nextInt(10);
    		}
    	}
    	return machinePenalties;
    }
    
    private void printMachinePenalties() {
    	for(int i = 0; i < 8; i++) {
    		System.out.println(Arrays.toString(this.machinePenalties[i]));
    	}
    	System.out.println("");
    }
    
    private Character[] newCharArray(Character[] charArray) {
    	Character[] newCharArray = new Character[8];
    	List<Character> list = new ArrayList<>();
    	for(Character c : charArray) {
    		list.add(c);
    	}
    	Collections.shuffle(list);
    	for(int i = 0; i < list.size(); i++) {
    		newCharArray[i] = list.get(i);
    	}
    	return newCharArray;
    }
    
    private void printTasks(Character[] tasks) {
    	System.out.println(Arrays.toString(tasks));
    	System.out.println("");
    }
    
    private int newIndex(Random rng) {
    	int index = rng.nextInt(8);
    	return index;
    }
    
    private void printIndex(int i) {
    	System.out.println("Index: " + i);
    	System.out.println("");
    }
    
    private void printPenalty(int p) {
    	System.out.println("Penalty: " + p);
    	System.out.println("");
    }
    
    public static void main(String[] args) {
    	int[][] machinePenalties = new int[8][8];
    	Scratch scratch = new Scratch(machinePenalties);
    	Character[] tasks = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    	int index, penalty;
    	Random rng = new Random();
    	
    	for(int i = 0; i < 3; i++) {
    		//generate random 8 x 8 int array for machine penalties
    		scratch.machinePenalties = scratch.newMPArray(rng);
    		//print array to aid verification
    		scratch.printMachinePenalties();
    		//shuffle task chars to get random assignment
    		tasks = scratch.newCharArray(tasks);
    		scratch.printTasks(tasks);
    		//generate random index
    		index = scratch.newIndex(rng);
    		scratch.printIndex(index);
    		//use calc machine penalty method to get value for penalty
    		//test by checking against printed array
    		penalty = scratch.calcMachinePenalty(tasks, index);
    		scratch.printPenalty(penalty);
    	}
    }

}
