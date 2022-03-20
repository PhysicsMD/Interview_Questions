package question3;

import java.util.ArrayList;

public class Permuate {
	
	/**
	    * ArrayList<String> permutationList;
	    * list for all the permutation combinations
	    */
	ArrayList<String> permutationList;
	
	/**
	    * public Permuate(int[] intList)
	    * initializes the list of permutations
	    * then calls recursive helper
	    */
	public Permuate(int[] intList) 
	{
		permutationList = new ArrayList<String>();
		PermuateHelper(intList, "");
	}
	
	/**
	    * private void PermuateHelper(int[] intList, String runningString)
	    * for each item in the list, the recursive function breaks to another
	    * iteration until there is only one item left in the list
	    */
	private void PermuateHelper(int[] intList, String runningString) 
	{
		//check for only one item left in the intList
		if(intList.length == 1) 
		{
			permutationList.add(runningString);
		}
		else
		{
			//iterates through the whole list of integers
			for(int branchiterator = 0; branchiterator < intList.length ; branchiterator++) 
			{
				//create a temporary list to pass into the recursive helper
				int[] helperList = new int[intList.length -1];
				
				//for loop to remove the branch integer from next integer list
				for(int integerlistIterator = 0; integerlistIterator < intList.length-1; integerlistIterator++) 
				{
					//fill list for all the items before the current integer
					if(integerlistIterator<branchiterator) 
					{
						helperList[integerlistIterator] = intList[integerlistIterator];
					}
					else 
					{
						//conditional for filling the list with integers after the branch iterator
						helperList[integerlistIterator] = intList[integerlistIterator+1];
					}
				}
				//call the helper to the branch
				PermuateHelper(helperList, runningString + intList[branchiterator]);
			}
		}
	}
	
	/**
	    * public void printList() 
	    * prints the permutations into the console
	    */
	public void printList() 
	{
		System.out.println("Permutations List:");
		for(int i = 0; i < permutationList.size(); i++) 
		{
			System.out.println(permutationList.get(i));
		}
		
	}
	
}
