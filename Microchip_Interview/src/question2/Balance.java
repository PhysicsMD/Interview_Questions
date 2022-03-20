package question2;

/**
 * 
 * @author Alenn Wright
 *
 *public class Balance
 *
 *Description: Class wrapper for a Java stack class for checking 
 *for balanced strings of brackets
 *
 */

public class Balance {
	
	// a key that contains all the brackets
	private String bracketTypes = "({[)}]"; 
	
	private Balance() {}
	
	public boolean isBalance(String UserInput)
	{
		//create a stack to put brackets in for testing
		SimpleStackClass bracketStack = new SimpleStackClass();
		
		//loop through the whole input string
		for(int i = 0; i < UserInput.length(); i++) 
		{
			//check if character is a bracket
			//""+ became necessary because the strings had methods that was important for logic
			if(bracketTypes.contains(""+UserInput.charAt(i)))
			{
				//check to see if the bracket is an open bracket
				if((""+(bracketTypes.subSequence(0, 3))).contains(""+UserInput.charAt(i))) 
				{
					//if an open bracket, put it on the stack
					bracketStack.push(""+UserInput.charAt(i));
				}
				else 
				{
					//check if it is a closing bracket, and using the key, check if it matches the top of the stack
					if(bracketTypes.indexOf(UserInput.charAt(i))-3 == bracketTypes.indexOf(bracketStack.peekTop().charAt(0))) 
					{
						//remove matching bracket
						bracketStack.pop();
					}
					else 
					{
						//return false if bracket is mismatched
						return false;
					}
				}
			}
			//else continue
		}
		//if method ran through the input and there is anything left in the stack then input is not balanced
		return bracketStack.isEmpty();
	}
}
