package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.real.JavaList;

/**
 * Insertion sort takes one item at a time from input list and puts 
 * into sorted position in new list.
 * @author alexiskilayko
 *
 */
public class InsertSort {
	/**
	 * Insert sort returns a new list.
	 * @param unsorted, the input list to be sorted
	 * @return a new list
	 */
	public static ListADT<Integer> insertSort(ListADT<Integer> unsorted) {
		ListADT<Integer> sorted = new JavaList<>(); // List that as we will fill up will remain sorted.
		// Loop as long as unsorted still contains something.
		while (!unsorted.isEmpty()) {
			int size = sorted.size();
			// Save value of first item in unsorted list, then remove it.
			int insert = unsorted.getFront();
			unsorted.removeFront();
			// If nothing in sorted list yet, just add item.
			if (sorted.isEmpty()) {
				sorted.addBack(insert);
			} else { // Otherwise, loop over items in sorted list and compare to our insert value.
				for (int i = 0; i < size; i++) { 
					int current = sorted.getIndex(i);
					if (insert < current) {
						// Insert at the index of first item whose value is greater.
						sorted.addIndex(i, insert);
						break;
					} 
					// If we go through all items in list and insert is greater than all
					// of them, then add it to the back.
					if (insert > sorted.getBack()){
						sorted.addBack(insert);
						break;
					}
				}
			}
		}
		// Return our new list once unsorted has run out of items.
		return sorted;
	}
}

