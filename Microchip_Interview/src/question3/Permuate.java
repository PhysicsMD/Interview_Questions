package Question3;

import java.util.ArrayList;

public class Permuate {
	
	ArrayList<String> permutationList;
	
	public Permuate(int[] intList) 
	{
		permutationList = new ArrayList<String>();
		PermuateHelper(intList, "");
	}
	
	private void PermuateHelper(int[] intList, String runningString) 
	{
		if(intList.length == 1) 
		{
			permutationList.add(runningString);
		}
		else
		{
			for(int i = 0; i < intList.length ; i++) 
			{
				int[] helperList = new int[intList.length -1];
				for(int j = 0; j < intList.length-1; j++) 
				{
					if(j<i) 
					{
						helperList[j] = intList[j];
					}
					else 
					{
						helperList[j] = intList[j+1];
					}
				}
				PermuateHelper(helperList, runningString + intList[i]);
			}
		}
	}
	
	public void printList() 
	{
		System.out.println("Permutations List:");
		for(int i = 0; i < permutationList.size(); i++) 
		{
			System.out.println(permutationList.get(i));
		}
		
	}
	
}
