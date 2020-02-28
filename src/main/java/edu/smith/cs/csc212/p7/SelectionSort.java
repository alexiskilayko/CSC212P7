package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.adtr.ListADT;

/**
 * Selection sort finds the minimum value in a list and swaps it to the front until sorted.
 * @author alexiskilayko
 *
 */
public class SelectionSort {
	/**
	 * Selection sort sorts a list in place.
	 * @param input, the list to be sorted.
	 */
	public static void selectionSort(ListADT<Integer> input) {		
		int size = input.size();
		int start = 0; // Index value, increases every time we bring a value to front.
		int swap = 0; // Index value of position we will swap from.
		while (true) {
			boolean sorted = true;
			// Minimum starts as first value of input list.
			int min = input.getIndex(start);
			for (int i = start; i < size; i++) {
				// Save value of index we are currently looking at.
				int current = input.getIndex(i);
				// Compare current value to minimum.
				// If current is less than minimum, then we have a new minimum!
				if (min > current) {
					min = current;
					swap = i; // New index of position to swap from.
					sorted = false;
				}
			}
			if (sorted) {
				break;
			}
			// Once finished iterating over all the items in list,
			// now have minimum of list, whose index is saved in swap.
			input.swap(start, swap);
			start++; // Sorted an item of the list, so one less item to worry about.
		}
	}
}