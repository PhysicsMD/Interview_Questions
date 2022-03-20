package question2;

/**
 * 
 * @author Alenn Wright
 *
 *public class Balance extends Object
 *
 *Description: Class wrapper for a Java stack class 
 *following filo scheme for parsing a string of brackets
 *ensuring they are balanced
 *
 */

public class Balance {
	
	String bracketTypes = "({[)}]"; 
	
	public Balance() {}
	
	public boolean isBalance(String UserInput)
	{
		SimpleStackClass bracketStack = new SimpleStackClass();
		for(int i = 0; i < UserInput.length(); i++) 
		{
			if(bracketTypes.contains(""+UserInput.charAt(i)))
			{
				//logic
				if((""+(bracketTypes.subSequence(0, 3))).contains(""+UserInput.charAt(i))) 
				{
					bracketStack.push(""+UserInput.charAt(i));
				}
				else 
				{
					if(bracketTypes.indexOf(UserInput.charAt(i))-3 == bracketTypes.indexOf(bracketStack.peekTop().charAt(0))) 
					{
						bracketStack.pop();
					}
					else 
					{
						return false;
					}
				}
			}
			//else continue
		}
		return bracketStack.isEmpty();
	}
}
