package solution;

import java.util.ArrayList;

/**
 * @author Jacob Evans, Bodaniel Jeanes
 * 
 */
public class CargoInventory {
	int numStacks, maxHeight, maxContainers, currentContainers;
	ArrayList<ArrayList<ContainerLabel>> storage = new ArrayList<ArrayList<ContainerLabel>>();

	/*
	 * @bjeanes just so you don't have to read the specs to understand this
	 * cargo is stored relative to eachother in a stack, can only remove the top
	 * most container cargo identification is 8 digits, zero-filled 01200432
	 * first 3 are the kind, last 5 are their identifier stored the cargo in a
	 * 2d Array list, an Arraylist within an ArrayList, accessing them by
	 * getting their "kind" this assumes that when stuff is stored the first
	 * container stacks will be something like 0 then the next stack is 1 there
	 * no way to change the size of the ships storage toArray method will return
	 * the given kinds elements in an array, could iterate over this
	 * currentContainers attribute to keep a count of how many containers on the
	 * ship
	 */
	CargoInventory(Integer numStacks, Integer maxHeight, Integer maxContainers)
			throws CargoException, IllegalArgumentException {
		
		// No null parameters supported
		if(numStacks == null || maxHeight == null || maxContainers == null) {
			throw new IllegalArgumentException("Cannot have null values for any parameter");
		}
		
		// No negative numbers supported
		if ((numStacks < 0) || (maxHeight < 0) || (maxContainers < 0)) {
			throw new CargoException("Cannot have negative values");
		}
		
		this.numStacks = numStacks;
		this.maxHeight = maxHeight;
		this.maxContainers = maxContainers;
		this.currentContainers = 0;

		for (int i = 0; i < numStacks; i++) {
			storage.add(new ArrayList<ContainerLabel>());
		}
	}

	boolean isAccessible(ContainerLabel queryContainer)
			throws IllegalArgumentException {
		ContainerLabel topContainer;
		// may get a null ?
		if (queryContainer == null) {
			throw new IllegalArgumentException();
		}
		
		try {
			ArrayList<ContainerLabel> stack = storage.get(queryContainer.getKind());
			topContainer = stack.get(stack.size() - 1);
			
			if (topContainer.equals(queryContainer)) {
				return true;
			}
		} catch(IndexOutOfBoundsException e) {}

		return false;
	}

	boolean isOnboard(ContainerLabel queryContainer)
			throws IllegalArgumentException {
		if (queryContainer != null) {
			return storage.get(queryContainer.getKind()).contains(
					queryContainer);
		} else {
			throw new IllegalArgumentException();
		}
	}

	void loadContainer(ContainerLabel newContainer) throws CargoException,
			IllegalArgumentException {
		if(isOnboard(newContainer)) {
			throw new CargoException("Container is already on board");
		}
		
		if(newContainer.getKind() >= numStacks) {
			throw new CargoException("Container kind can not be stacked on this ship");
		}
		
		if(currentContainers >= maxContainers) {
			throw new CargoException("The ship is full and can not hold anymore containers");
		}
		
		storage.get(newContainer.getKind()).add(newContainer);
		currentContainers++;
	}

	ContainerLabel[] toArray(Integer kind) throws CargoException,
			IllegalArgumentException {		
		if (kind == null) {
			throw new IllegalArgumentException();
		}
		
		if (kind < 0 || kind > numStacks) {
			throw new CargoException("No such stack on this ship");
		}
		
		ContainerLabel[] returnArray = new ContainerLabel[maxHeight];
		
		if (kind < numStacks) {
			return storage.get(kind).toArray(returnArray);
		} else {
			throw new CargoException("Kind it out of our range");
		}
	}

	void unloadContainer(ContainerLabel oldContainer) throws CargoException,
			IllegalArgumentException {
		// check if the cargo is at the top of the stack
		if (oldContainer == null) {
			throw new IllegalArgumentException();
		}
		
		if (isOnboard(oldContainer)) {
			if (isAccessible(oldContainer)) {
				// remove the container
				storage.get(oldContainer.getKind()).remove(oldContainer);
				currentContainers--;
			} else {
				throw new CargoException("Cannot access the container");
			}
		}
	}
}
