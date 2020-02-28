package edu.smith.cs.csc212.p7;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.real.JavaList;

public class TestSorting {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(ListADT<Integer> items) {
		for (int i=0; i<items.size()-1; i++) {
			if (items.getIndex(i) > items.getIndex(i+1)) {
				System.err.println("items out of order: "+items.getIndex(i)+", "+items.getIndex(i+1) + " at index="+i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Here's some data!
	 */
	private static int[] data = {9,8,7,6,5,4,3,2,1};
	
	@Test
	public void testBubbleSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		Random rand = new Random(13);
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		sortMe.shuffle(rand);
		System.out.println(sortMe.toJava());
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}

	@Test
	public void testClassBubbleSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
		
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	// Selection sort tests.
	
	@Test
	public void testSelectionSort() {
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		Assert.assertEquals(9, sortMe.size());
		
		Random rand = new Random(13);
		sortMe.shuffle(rand);
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		Assert.assertEquals(9, sortMe.size());
	}
	
	@Test
	public void testClassSelectionSort() {
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
		
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		Assert.assertEquals(9, sortMe.size());
	}
	
	// Insertion sort tests.
	
	@Test
	public void testInsertSort() {
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		ListADT<Integer> sorted = InsertSort.insertSort(sortMe);
		Assert.assertEquals(9, sorted.size());
		Assert.assertTrue(checkSorted(sorted));
	}
		
	@Test
	public void testShuffleInsertSort() {
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		Random rand = new Random(13);
		sortMe.shuffle(rand);
		ListADT<Integer> sortedShuffle = InsertSort.insertSort(sortMe);
		Assert.assertEquals(9, sortedShuffle.size());
		Assert.assertTrue(checkSorted(sortedShuffle));
	}
	
	@Test
	public void testClassInsertSort() {
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
		
		ListADT<Integer> sorted = InsertSort.insertSort(sortMe);
		Assert.assertEquals(9, sorted.size());
		Assert.assertTrue(checkSorted(sorted));
	}
	
	// Combine helper tests.
	// We can't use a shuffle sort for this because the lists must be sorted.
	
	@Test
	public void testCombineHelper() {
		ListADT<Integer> sort1 = new JavaList<>(Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15));
		ListADT<Integer> sort2 = new JavaList<>(Arrays.asList(1, 2, 4, 6, 8, 9, 10, 12, 14));
		ListADT<Integer> sorted = MergeSort.combineHelper(sort1, sort2);
		Assert.assertEquals(17, sorted.size());
		Assert.assertTrue(checkSorted(sorted));
	}
	
	// Recursive merge sort tests.
	
	@Test
	public void testRecursiveMerge() {
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		ListADT<Integer> sorted = MergeSort.recursive(sortMe);
		System.out.println(sorted);
		Assert.assertTrue(checkSorted(sorted));
		Assert.assertEquals(9, sorted.size());
	}
	
	@Test
	public void testShuffleRecursive() {
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		Random rand = new Random(13);
		sortMe.shuffle(rand);
		ListADT<Integer> sortedShuffle = InsertSort.insertSort(sortMe);
		Assert.assertEquals(9, sortedShuffle.size());
		Assert.assertTrue(checkSorted(sortedShuffle));
	}
	
	@Test
	public void testClassRecursive() {
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
		
		ListADT<Integer> sorted = InsertSort.insertSort(sortMe);
		Assert.assertEquals(9, sorted.size());
		Assert.assertTrue(checkSorted(sorted));
	}

	// Iterative merge sort tests.
	
	@Test
	public void testIterativeMerge() {
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		ListADT<Integer> sorted = MergeSort.iterative(sortMe);
		System.out.println(sorted);
		Assert.assertTrue(checkSorted(sorted));
		Assert.assertEquals(9, sorted.size());
	}
	
	@Test
	public void testShuffleIterative() {
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		Random rand = new Random(13);
		sortMe.shuffle(rand);
		ListADT<Integer> sortedShuffle = InsertSort.insertSort(sortMe);
		Assert.assertEquals(9, sortedShuffle.size());
		Assert.assertTrue(checkSorted(sortedShuffle));
	}
	
	@Test
	public void testClassIterative() {
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
		
		ListADT<Integer> sorted = InsertSort.insertSort(sortMe);
		Assert.assertEquals(9, sorted.size());
		Assert.assertTrue(checkSorted(sorted));
	}
	
}
