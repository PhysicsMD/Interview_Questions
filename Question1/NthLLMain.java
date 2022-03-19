package Question1;

public class NthLLMain {

	public static void main(String[] args)
    {
		BasicLLClass list = new BasicLLClass();
		list.setAtIndex(0, 6, BasicLLClass.INSERT_BEFORE);
		list.setAtIndex(0, 5, BasicLLClass.INSERT_BEFORE);
		list.setAtIndex(0, 4, BasicLLClass.INSERT_BEFORE);
		list.setAtIndex(0, 3, BasicLLClass.INSERT_BEFORE);
		list.setAtIndex(0, 2, BasicLLClass.INSERT_BEFORE);
		list.setAtIndex(0, 1, BasicLLClass.INSERT_BEFORE);
		
		System.out.println(list.getNthfromLast(0));
		System.out.println(list.getNthfromLast(3));
		System.out.println(list.getNthfromLast(-2));
    }
	
}
