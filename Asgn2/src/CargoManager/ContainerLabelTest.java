package CargoManager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jacob Evans, Bodaniel Jeanes
 *
 */

public class ContainerLabelTest {
	private ContainerLabel labelOne;

	@Before
	public void setUp() throws LabelException {
		labelOne = new ContainerLabel(0,1,1,1);
	}
	
	//TODO add some tests for when the length of kind is smaller than what it should be
	@Test
	public void testConstructor()
	{
		 assertEquals(new Integer(0), labelOne.getKind());
		 assertEquals(new Integer(1), labelOne.getIdentifier());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsIllegalArgumentExceptionWhenFirstArgumentIsNull() throws LabelException {
		new ContainerLabel(null, 0, 0, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsIllegalArgumentExceptionWhenSecondArgumentIsNull() throws LabelException {
		new ContainerLabel(0, null, 0, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsIllegalArgumentExceptionWhenThirdArgumentIsNull() throws LabelException {
		new ContainerLabel(0, 0, null, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsIllegalArgumentExceptionWhenFourthArgumentIsNull() throws LabelException {
		new ContainerLabel(0, 0, 0, null);
	}
	
	@Test
	public void testToString()
	{
		assertTrue(labelOne.toString().equals("00000001"));
	}
	
	@Test
	public void testToStringTwo() throws LabelException
	{
		ContainerLabel labelTwo = new ContainerLabel(12,2,432,3);
		assertTrue(labelTwo.toString().equals("01200432"));
	}
	
	@Test(expected = LabelException.class)
	public void illegalConstructorNegativeValues() throws LabelException
	{
		ContainerLabel badLabel = new ContainerLabel(-1,-1,-1,-1);
	}

	@Test(expected = LabelException.class)
	public void illegalConstructorBadRange() throws LabelException
	{
		ContainerLabel badLabel = new ContainerLabel(0,7,1,5);
	}
	
}
