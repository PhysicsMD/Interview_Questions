package Question3;

public class PermuateMain {
	
	public static void main(String[] args)
    {
		int[] testlist1 = {2,5,7,3,6};
		Permuate test1 = new Permuate(testlist1);
		test1.printList();
		
		int[] testlist2 = {2,5,7,3,6,84,255,1};
		Permuate test2 = new Permuate(testlist2);
		test2.printList();
		
    }
	
}
