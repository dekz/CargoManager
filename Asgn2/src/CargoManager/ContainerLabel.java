package CargoManager;

import java.lang.*;

/**
 * @author Jacob Evans, Bodaniel Jeanes
 *
 */

public class ContainerLabel {
	
	int identifier, kind, kindLength, identifierLength;
	
	//TODO add exceptions for constructor
	
	ContainerLabel(int a_kind, int a_kindLength, int a_identifier, int a_identifierLength) throws LabelException
	{
		if ((a_kind < 0) || (a_identifier < 0) || (a_kindLength < 0) || (a_identifierLength < 0))
			throw new LabelException("Cannot give negative values to cargo");
		if ((a_kindLength > 3) || (a_identifierLength > 5))
			throw new LabelException("Cannot assign that high a value");
		//TODO test to see if the length is larger than allowed values, smaller than the length
		
		this.identifier = a_identifier;
		this.kind = a_kind;
		this.kindLength = a_kindLength;
		this.identifierLength = a_identifierLength;
		
	}
	
	int getIdentifier()
	{
		return this.identifier;
	}
	
	int getKind()
	{
		return this.kind;
	}
	
	boolean matches(ContainerLabel a_label) throws IllegalArgumentException
	{
		if (a_label == null)
			throw new IllegalArgumentException();
		
		if ((this.getKind() == a_label.getKind()) && (this.getIdentifier() == a_label.getIdentifier()))
		{
			return true;
		}
		else return false;
	}
	
	@Override public String toString()
	{
		String localString = "";
		int localKindLength = kindLength;
		int localIdentLength = identifierLength;
		//quite messy
		while(localKindLength < 3)
		{
			localString += "0";
			localKindLength++;
		}
		localString += kind;
		
		while(localIdentLength < 5)
		{
			localString += "0";
			localIdentLength++;
		}
		localString += identifier;

		return localString;
	}
}

