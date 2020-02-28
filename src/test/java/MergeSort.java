

import java.util.Arrays;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.errors.EmptyListError;
import edu.smith.cs.csc212.adtr.real.JavaList;
//import edu.smith.cs.csc212.adtr.real.DoublyLinkedList;

public class MergeSort {

	public static ListADT<Integer> combineHelper(ListADT<Integer> listOne, ListADT<Integer> listTwo) {
		ListADT<Integer> sorted = new JavaList<>();
		while ((listOne.isEmpty() == false) && (listTwo.isEmpty() == false)) {
			int oneFront = listOne.getFront();
			int twoFront = listTwo.getFront();
			if (oneFront <= twoFront) {
				listOne.removeFront();
				sorted.addBack(oneFront);
			} else {
				listTwo.removeFront();
				sorted.addBack(twoFront);
			}
		}
		if (listOne.isEmpty()) {
			sorted.addAll(listTwo);
		}
		if (listTwo.isEmpty()) {
			sorted.addAll(listOne);
		}
		return sorted;
	}
	

	public static ListADT<Integer> recursive(ListADT<Integer> unsorted) {
		if (unsorted.size() == 0) {
			throw new EmptyListError();
		} else if (unsorted.size() == 1) {
			return unsorted;
		}
		int mid = unsorted.size()/2;
		return combineHelper(recursive(unsorted.slice(0, mid)), 
				recursive(unsorted.slice(mid, unsorted.size())
						));
		//return unsorted;
	}
	
	public static ListADT<Integer> iterative(ListADT<Integer> unsorted) {
		int size = unsorted.size()-1;
		ListADT<Integer> masterList = null;
		for (int i = 0; i == size; i++) {
		   //ListADT<Integer> uniqueList = null;
		   int removed = unsorted.removeFront();
		   unsorted.removeFront();
		   ListADT<Integer> uniqueList = new JavaList<>(Arrays.asList(removed));
		   //uniqueList.addBack(removed);
		   masterList.addAll(uniqueList);
		 }
		//combine(masterList.getFront(), masterList.getFront().after);
		return unsorted;
	}
}
