/**
 * 
 */
package CargoManager;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsIllegalArgumentExceptionWhenFirstParameterIsNull() throws CargoException {
		new CargoInventory(null, 1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsIllegalArgumentExceptionWhenSecondParameterIsNull() throws CargoException {
		new CargoInventory(1, null, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsIllegalArgumentExceptionWhenThirdParameterIsNull() throws CargoException {
		new CargoInventory(1, 1, null);
	}
	
	@Test
	public void testAdddingContainer() throws LabelException, CargoException
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
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		
		inventoryOne.loadContainer(containerOne);
		inventoryOne.loadContainer(containerTwo);
		
		assertFalse(inventoryOne.isAccessible(containerOne));
	}
	
	/**
	 * This should not throw any exceptions as the container is the top of the stack
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test
	public void testUnloadTopContainer() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		
		inventoryOne.loadContainer(containerOne);
		inventoryOne.loadContainer(containerTwo);
		
		inventoryOne.unloadContainer(containerTwo);
	}
	
	/**
	 * This should throw a CargoException as the container isn't at the top of the stack
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testUnloadBottomContainer() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		
		inventoryOne.loadContainer(containerOne);
		inventoryOne.loadContainer(containerTwo);
		
		inventoryOne.unloadContainer(containerOne);
	}
	
	/**
	 * This should throw a IllegalArgumentException as we can't unload null
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testUnloadNullContainerThrowsExceptionOnInventoryWithCargo() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(1,1,1,1);
		inventoryOne.loadContainer(containerOne);
		
		inventoryOne.unloadContainer(null);
	}
	
	/**
	 * This should throw a IllegalArgumentException as we can't unload null
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testUnloadNullContainerThrowsExceptionOnInventoryWithoutCargo() throws LabelException, CargoException
	{	
		inventoryOne.unloadContainer(null);
	}
	
	/**
	 * This should throw a CargoException as the given containers are out of the range for our ship
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testRangeOfCargoInventory() throws LabelException, CargoException
	{
		ContainerLabel containerOne = new ContainerLabel(10,1,1,1);
		ContainerLabel containerTwo = new ContainerLabel(10,1,1,2);
		
		inventoryOne.loadContainer(containerOne);
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
		ContainerLabel containerTwo = new ContainerLabel(1,1,1,2);
		ContainerLabel containerThree = new ContainerLabel(1,1,1,3);
		ContainerLabel containerToNotInclude = new ContainerLabel(2,1,1,1);
		
		inventoryOne.loadContainer(containerOne);
		inventoryOne.loadContainer(containerTwo);
		inventoryOne.loadContainer(containerThree);
		inventoryOne.loadContainer(containerToNotInclude);
		
		ContainerLabel[] arrayLabels = {containerOne, containerTwo, containerThree};
		
		assertArrayEquals(arrayLabels, inventoryOne.toArray(1));
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
		ContainerLabel containerTwo = new ContainerLabel(1,1,2,1);
		ContainerLabel containerThree = new ContainerLabel(1,1,3,1);
		
		inventoryOne.loadContainer(containerOne);
		inventoryOne.loadContainer(containerTwo);
		inventoryOne.loadContainer(containerThree);
		
		ContainerLabel[] arrayLabels = new ContainerLabel[3];
		arrayLabels = inventoryOne.toArray(1);
		
		assertEquals("00100002", arrayLabels[1].toString());
	}
	
	/**
	 * This will test to see if an exception is thrown if there are too many kinds of containers for the ship
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testTooManyContainersForShip() throws LabelException, CargoException
	{
		CargoInventory inventoryTwo   = new CargoInventory(100, 100, 2);
		
		// 3 containers regardless of kind for a ship that can only hold 2
		ContainerLabel containerOne   = new ContainerLabel(1,1,1,1);
		ContainerLabel containerTwo   = new ContainerLabel(1,1,2,1);
		ContainerLabel containerThree = new ContainerLabel(1,1,3,1);
		
		try {
			inventoryTwo.loadContainer(containerOne);
			inventoryTwo.loadContainer(containerTwo);
		} catch(CargoException e) {
			fail("CargoException thrown too early");
		}

		// This call should raise an exception as only 2 containers are allowed on the ship
		inventoryTwo.loadContainer(containerThree);
	}
	
	/**
	 * This will test to see if an exception is thrown if there are too many containers for a stack of a certain kind
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testTooManyContainersForStack() throws LabelException, CargoException
	{
		CargoInventory inventoryTwo   = new CargoInventory(100,2,100);
		
		// 3 containers of the same kind (so will be in the same stack)
		ContainerLabel containerOne   = new ContainerLabel(1,1,1,1);
		ContainerLabel containerTwo   = new ContainerLabel(1,1,2,1);
		ContainerLabel containerThree = new ContainerLabel(1,1,3,1);
		
		try {
			inventoryTwo.loadContainer(containerOne);
			inventoryTwo.loadContainer(containerTwo);
		} catch(CargoException e) {
			fail("CargoException thrown too early");
		}
		
		// This call should raise an exception as the stack height is only 2
		inventoryTwo.loadContainer(containerThree); 
	}
	
	/**
	 * This will test to see if an exception is thrown if there are too many kinds of containers for a ship
	 * @throws LabelException
	 * @throws CargoException
	 */
	@Test(expected = CargoException.class)
	public void testTooManyContainersForNumberOfStackKinds() throws LabelException, CargoException
	{
		CargoInventory inventoryTwo   = new CargoInventory(2,100,100);
		
		// Three different kinds of containers
		ContainerLabel containerOne   = new ContainerLabel(1,1,1,1);
		ContainerLabel containerTwo   = new ContainerLabel(2,1,1,1);
		ContainerLabel containerThree = new ContainerLabel(3,1,1,1);
		
		try {
			inventoryTwo.loadContainer(containerOne);
			inventoryTwo.loadContainer(containerTwo);
		} catch(CargoException e) {
			fail("CargoException thrown too early");
		}
		
		// This call should raise an exception as their is only room for two stacks
		inventoryTwo.loadContainer(containerThree); 
	}
}
