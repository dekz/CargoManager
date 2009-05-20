package solution;

public class CargoException extends Exception {

	CargoException()
	{
		super("Cargo Exception");
	}
	CargoException(String message)
	{
		super("Cargo Exception: " + message);
	}
}
