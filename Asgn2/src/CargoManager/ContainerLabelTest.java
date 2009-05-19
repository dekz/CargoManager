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
	public void setUp() throws Exception {
		labelOne = new ContainerLabel(0,1,1,1);
	}
	//TODO add some tests for when the length of kind is smaller than what it should be
	@Test
	public void testConstructor()
	{
		 assertTrue(labelOne.getKind() == 0);
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
