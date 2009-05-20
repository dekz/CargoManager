package solution;

public class LabelException extends Exception {
	LabelException()
	{
		super("LabelException");
	}
	
	LabelException(String message)
	{
		super("LabelException: " + message); 
	}
}
