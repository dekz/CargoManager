/**
 * 
 */
package CargoManager;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jacob Evans, Bodaniel Jeanes
 *
 */
public class CargoInventoryTest {

	CargoInventory inventoryOne;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		inventoryOne = new CargoInventory(3,3,10);
	}
	
	@Test
	public void addContainer() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
	}
	
	@Test
	public void testOnBoard() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		assertTrue(inventoryOne.isOnboard(containerOne));
	}
	
	/**
	 * This will return false as the item is not aboard the cargo ship
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test
	public void testOnBoardNotOnboard() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		assertFalse(inventoryOne.isOnboard(containerOne));
	}
	
	/**
	 * This will throw a IllegalArgumentException as its given a null object
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testOnBoardNullObject() throws LabelException, CargoException
	{
		ContainerLabel containerOne = null;
		assertFalse(inventoryOne.isOnboard(containerOne));
	}
	
	
	/**
	 * This should return true as the container is the top of the stack
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test
	public void testIsAccessible() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		assertTrue(inventoryOne.isAccessible(containerOne));
	}
	
	/**
	 * This should return false as the container isn't the top of the stack
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test
	public void testIsAccessibleTwoCargo() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		inventoryOne.loadContainer(containerTwo);
		assertFalse(inventoryOne.isAccessible(containerOne));
	}
	
	/**
	 * This should return true as the container is the top of the stack
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test
	public void testUnloadTopContainer() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		inventoryOne.loadContainer(containerTwo);
		inventoryOne.unloadContainer(containerTwo);
	}
	
	/**
	 * This should return false as the container isn't at the top of the stack
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testUnloadBottomContainer() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		inventoryOne.loadContainer(containerTwo);
		inventoryOne.unloadContainer(containerOne);
	}
	
	/**
	 * This should return false as the container isn't at the top of the stack
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testUnloadNullContainer() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		ContainerLabel containerTwo = null;
		inventoryOne.unloadContainer(containerTwo);
	}
	
	/**
	 * This should throw an exception as the given containers are out of the range for our ship
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testRangeOfCargoInventory() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(10,1,1,1);
		inventoryOne.loadContainer(containerOne);
		ContainerLabel containerTwo = new ContainerLabel(10,1,1,2);
		inventoryOne.loadContainer(containerTwo);
		inventoryOne.unloadContainer(containerOne);
	}
	
	/**
	 * This will return the 2nd stack of the inventory in the form of an array
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test
	public void testToArray() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		inventoryOne.loadContainer(containerTwo);
		ContainerLabel containerThree = new ContainerLabel(1,1,1,3);
		inventoryOne.loadContainer(containerThree);
		ContainerLabel[] arrayLabels = new ContainerLabel[3];
		arrayLabels = inventoryOne.toArray(1);
	}
	
	/**
	 * This will test to see if the array returns properly and returns the objects in the right order
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test
	public void testToArrayAndToString() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		ContainerLabel containerTwo = new ContainerLabel(1,1,2,1);
		inventoryOne.loadContainer(containerTwo);
		ContainerLabel containerThree = new ContainerLabel(1,1,3,1);
		inventoryOne.loadContainer(containerThree);
		ContainerLabel[] arrayLabels = new ContainerLabel[3];
		arrayLabels = inventoryOne.toArray(1);
		assertEquals(arrayLabels[1].toString(),"00100002");
	}
	
	/**
	 * This will test to see if an exception is thrown if there are too many containers
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testTooManyContainers() throws LabelException, CargoException
	{
		CargoInventory inventoryTwo = new CargoInventory(2,1,1);
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryTwo.loadContainer(containerOne);
		ContainerLabel containerTwo = new ContainerLabel(1,1,2,1);
		inventoryTwo.loadContainer(containerTwo);
		ContainerLabel containerThree = new ContainerLabel(1,1,3,1);
		inventoryTwo.loadContainer(containerThree);

	}
	

}
