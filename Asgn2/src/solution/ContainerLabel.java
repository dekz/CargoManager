package solution;

/**
 * @author Jacob Evans, Bodaniel Jeanes
 * 
 */

public class ContainerLabel {

	int identifier, kind, kindLength, identifierLength;

	ContainerLabel(Integer a_kind, Integer a_kindLength, Integer a_identifier,
			Integer a_identifierLength) throws LabelException,
			IllegalArgumentException {
		if (a_kind == null || a_identifier == null || a_kindLength == null
				|| a_identifierLength == null)
			throw new IllegalArgumentException(
					"Cannot give null values for any parameters");
		if ((a_kind < 0) || (a_identifier < 0) || (a_kindLength < 0)
				|| (a_identifierLength < 0))
			throw new LabelException("Cannot give negative values to cargo");
		if ((a_kindLength > 3) || (a_identifierLength > 5))
			throw new LabelException("Cannot assign that high a value");
		// TODO test to see if the length is larger than allowed values, smaller
		// than the length

		this.identifier = a_identifier;
		this.kind = a_kind;
		this.kindLength = a_kindLength;
		this.identifierLength = a_identifierLength;

	}

	Integer getIdentifier() {
		return this.identifier;
	}

	Integer getKind() {
		return this.kind;
	}

	boolean matches(ContainerLabel a_label) throws IllegalArgumentException {
		if (a_label == null)
			throw new IllegalArgumentException();

		if ((this.getKind() == a_label.getKind())
				&& (this.getIdentifier() == a_label.getIdentifier()))
			return true;

		return false;
	}

	@Override
	public String toString() {
		return String.format("%1$03d%2$05d", kind, identifier);
	}
}
