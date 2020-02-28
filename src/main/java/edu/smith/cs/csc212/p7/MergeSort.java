package edu.smith.cs.csc212.p7;

import java.util.Arrays;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.real.JavaList;
import edu.smith.cs.csc212.adtr.real.DoublyLinkedList;

/**
 * There are a few different approaches to merge sort.
 * @author alexiskilayko
 *
 */
public class MergeSort {
	/**
	 * The combine helper method takes two sorted lists, compares the front of each,
	 * and removes the smaller of the two from its respective list and adds it to a new list.
	 * @param listOne (must be sorted upon input)
	 * @param listTwo (must be sorted upon input)
	 * @return sorted, a merge of the two input lists
	 */
	public static ListADT<Integer> combineHelper(ListADT<Integer> listOne, ListADT<Integer> listTwo) {
		ListADT<Integer> sorted = new JavaList<>(); // List that we will fill up.
		// Loop as long as neither list is empty.
		while ((listOne.isEmpty() == false) && (listTwo.isEmpty() == false)) {
			// Get the first value of each input list.
			int oneFront = listOne.getFront();
			int twoFront = listTwo.getFront();
			// If the value from list one is smaller, remove from list one and add to sorted list.
			if (oneFront <= twoFront) {
				listOne.removeFront();
				sorted.addBack(oneFront);
			} else { // Else, value from list two is smaller, so remove from list two and add to sorted list.
				listTwo.removeFront();
				sorted.addBack(twoFront);
			}
		}
		// Once one of the lists is empty, add the remains of the other list to the sorted list.
		if (listOne.isEmpty()) {
			sorted.addAll(listTwo);
		}
		if (listTwo.isEmpty()) {
			sorted.addAll(listOne);
		}
		return sorted;
	}

	/**
	 * Recursive method of merge sort. Split a list into halves until we have
	 * items by themselves, then combine these single items together into a sorted list.
	 * @param unsorted, input list
	 * @return new list
	 */
	public static ListADT<Integer> recursive(ListADT<Integer> unsorted) {
		// Base cases.
		if (unsorted.size() == 0) {
			return unsorted;
		} else if (unsorted.size() == 1) {
			return unsorted;
		}
		int mid = unsorted.size()/2;
		return combineHelper(
				recursive(unsorted.slice(0, mid)), 
				recursive(unsorted.slice(mid, unsorted.size())
						));
	}
	
	/**
	 * Iterative method of merge sort. Put every item of input list into its own list,
	 * put these values into one big list, then combine the first two values of list and
	 * add their merged version to back until sorted.
	 * @param unsorted, input list
	 * @return new list
	 */
	public static ListADT<Integer> iterative(ListADT<Integer> unsorted) {
		DoublyLinkedList<ListADT<Integer>> workQueue = new DoublyLinkedList<>();
		// Take every item of input list and put it into its own list.
		// Then add each to work queue.
		for (int item : unsorted) {
			ListADT<Integer> singleton = new JavaList<>(Arrays.asList(item));
			workQueue.addBack(singleton);
		}
		// Until our work queue of lists contains only one list (which should be sorted),
		// combine the first two lists, remove them from front and their merge to the back.
		while (workQueue.size() != 1) {
			ListADT<Integer> firstItem = workQueue.getFront();
			ListADT<Integer> secondItem = workQueue.getIndex(1);
			ListADT<Integer> merged = combineHelper(firstItem, secondItem);
			workQueue.removeFront();
			workQueue.removeFront();
			workQueue.addBack(merged);
		}
		// Our work queue should now contain one sorted list.
		ListADT<Integer> sorted = workQueue.getFront();
		return sorted;
	}
}
