package Samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {
	public static final int BUBBLE_SORT = 0;
	public static final int INSERTION_SORT = 1;
	public static final int QUICK_SORT = 2;
	public static final int MERGE_SORT = 3;
	public static final int BINARY_SEARCH = 4;
	public static final int SELECTION_SORT = 5;
	public static String[] SORT_TYPES = new String[] {"Bubble Sort", "Insertion Sort", 
		"Quick Sort", "Merge Sort", "Binary Search", "Selection Sort"};
//	public static int[] NUMBERS_ARRAY = new int[] {9, 5, 8, 1, 3, 7, 4, 2, 6, 14, 10, 15, 13, 12, 11, 19, 18};
	public static int[] NUMBERS_ARRAY = new int[] {9, 5, 8, 1, 3, 7, 4, 2, 6};
	private static void swap(int[] numbers, int firstIndex, int secondIndex) {
		int temp = numbers[firstIndex];
		numbers[firstIndex] = numbers[secondIndex];
		numbers[secondIndex] = temp;
	}
	
	private static void bubbleSort(int[] numbers) {
		boolean swapped = false;
		int loopCount = 0;
		do {
			swapped = false;
			for (int i = 0; i < numbers.length - 1; i++) {
				loopCount++;
				if (numbers[i] > numbers[i + 1]) {
					swap(numbers, i, i + 1);
					swapped = true;
				}
			}
		} while (swapped);
		System.out.println("Loop count = " + loopCount);
		System.out.println("Sorted array = " + Arrays.toString(numbers));
	}
	
	private static void insertionSort(int[] numbers) {
		int loopCount = 0;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < i; j++) {
				loopCount++;
				if (numbers[j] > numbers[j + 1]) {
					swap(numbers, j, j + 1);
				}
			}
		}
		System.out.println("Loop count = " + loopCount);
		System.out.println("Sorted array = " + Arrays.toString(numbers));
	}
	
	private static void selectionSort(int[] numbers) {
		int loopCount = 0;
		for (int i = 0; i < numbers.length; i++) {
			int min  = i;
			for (int j = i + 1; j < numbers.length - 1; j++) {
				loopCount++;
				if (numbers[min] > numbers[j]) {
					min = j;
				}
			}
			if (i != min) {
				swap(numbers, min, i);
			}
		}
		System.out.println("Loop count = " + loopCount);
	}

	private static void quickSort(int[] numbers, int left, int right) {
	    if (left >= right) {
	        return;
        }
        int pivot = numbers[(left + right) / 2];
	    int index = partition(numbers, left, right, pivot);
	    quickSort(numbers, left, index - 1);
	    quickSort(numbers, index, right);
    }

    private static int partition(int[] numbers, int left, int right, int pivot) {
	    while (left <= right) {
	        while (numbers[left] < pivot) {
	            left++;
            }
            while (numbers[right] > pivot) {
	            right--;
            }
            if (numbers[left] >= numbers[right]) {
	            swap(numbers, left, right);
	            left++;
	            right--;
            }
        }
	    return left;
    }


	private static List<Integer> quickSortJava(List<Integer> numbers) {
		if (numbers.size() < 2) {
			return numbers;
		}
		int pivot = numbers.get(0);
		ArrayList<Integer> lower = new ArrayList<Integer>();
		ArrayList<Integer> higher = new ArrayList<Integer>();
		for (int i = 1; i < numbers.size(); i++) {
			if (numbers.get(i) < pivot) {
				lower.add(numbers.get(i));
			} else {
				higher.add(numbers.get(i));
			}
		}
		List<Integer> sortedList = quickSortJava(lower);
		sortedList.add(pivot);
		sortedList.addAll(quickSortJava(higher));
		return sortedList;
	}
	private static void mergeSort(int[] numbers, int[] sorted, int leftStart, int rightEnd) {
	    if (leftStart >= rightEnd) {
	        return;
        }
        int middle = (leftStart + rightEnd) / 2;
	    mergeSort(numbers, sorted, leftStart, middle);
	    mergeSort(numbers, sorted, middle + 1, rightEnd);
	    merge(numbers, sorted, leftStart, rightEnd);
    }
    private static void merge(int[] numbers, int[] sorted, int leftStart, int rightEnd) {
	    int leftEnd = (leftStart + rightEnd) / 2;
	    int rightStart = leftEnd + 1;
	    int size = rightEnd - leftStart + 1;

	    int left = leftStart;
	    int right = rightStart;
	    int index = leftStart;

	    while(left <= leftEnd && right <= rightEnd) {
	        if (numbers[left] < numbers[right]) {
	            sorted[index] = numbers[left];
	            left++;
            } else {
	            sorted[index] = numbers[right];
	            right++;
            }
            index++;
        }
        System.arraycopy(numbers, left, sorted, index, leftEnd - left + 1);
        System.arraycopy(numbers, right, sorted, index, rightEnd - right + 1);
        System.arraycopy(sorted, leftStart, numbers, leftStart, size);
    }

    private static List<Integer> mergeSortJava(List<Integer> values) {
	    if (values.size() < 2) {
	        return values;
        }
        int middle = values.size() / 2;
	    List<Integer> leftList = values.subList(0, middle);
	    List<Integer> rightList = values.subList(middle, values.size());
	    return merge(mergeSortJava(leftList), mergeSortJava(rightList));

    }

    private static List<Integer> merge(List<Integer> leftList, List<Integer> rightList) {
	    List<Integer> mergedList = new ArrayList<>();
	    int left = 0, right = 0;
	    int index = 0;
	    while(left < leftList.size() && right < rightList.size()) {
	        if (leftList.get(left) < rightList.get(right)) {
	            mergedList.add(leftList.get(left));
	            left++;
            } else {
                mergedList.add(rightList.get(right));
                right++;
            }
        }

        while (left < leftList.size()) {
            mergedList.add(leftList.get(left));
            left++;
        }

        while (right < rightList.size()) {
            mergedList.add(rightList.get(right));
            right++;
        }

        return mergedList;
    }


//
//	private static List<Integer> mergeSort(List<Integer> values) {
//		if (values.size() < 2) {
//			return values;
//		}
//		List<Integer> leftList = values.subList(0, values.size() / 2);
//		List<Integer> rightList = values.subList(values.size() / 2, values.size());
//		return merge(mergeSort(leftList), mergeSort(rightList));
//	}
//	private static List<Integer> merge(List<Integer> leftList, List<Integer> rightList) {
//		List<Integer> mergedList = new ArrayList<Integer>();
//		int leftPtr = 0, rightPtr = 0;
//		while (leftPtr < leftList.size() && rightPtr < rightList.size()) {
//			if (leftList.get(leftPtr) < rightList.get(rightPtr)) {
//				mergedList.add(leftList.get(leftPtr));
//				leftPtr++;
//			} else {
//				mergedList.add(rightList.get(rightPtr));
//				rightPtr++;
//			}
//		}
//		while (leftPtr < leftList.size()){
//			mergedList.add(leftList.get(leftPtr));
//			leftPtr++;
//		}
//		while (rightPtr < rightList.size()){
//			mergedList.add(rightList.get(rightPtr));
//			rightPtr++;
//		}
//
//		return mergedList;
//	}
	
	private static boolean binarySearch(List<Integer> numbers, Integer value) {
		if (numbers == null || numbers.isEmpty()) {
			return false;
		}
		
		Integer comparison = numbers.get(numbers.size()/2);
		if (value.equals(comparison)) {
			return true;
		}
		
		if (value < comparison) {
			return binarySearch(numbers.subList(0, numbers.size()/2), value);
		} else {
			return binarySearch(numbers.subList(numbers.size()/2 + 1, numbers.size()), value);
		}
	}
	
	public static void testSorting(int sortType) {
		List<Integer> intList = new ArrayList<Integer>();
	    for (int index = 0; index < NUMBERS_ARRAY.length; index++) {
	        intList.add(NUMBERS_ARRAY[index]);
	    }
	    List<Integer> sortedList = null;
		long startTime = System.nanoTime();
		switch(sortType) {
			case BUBBLE_SORT:
				bubbleSort(NUMBERS_ARRAY);
				break;
			case INSERTION_SORT:
				insertionSort(NUMBERS_ARRAY);
				break;
			case QUICK_SORT:
				quickSort(NUMBERS_ARRAY, 0, NUMBERS_ARRAY.length - 1);
//				sortedList = quickSortJava(intList);
				break;
			case MERGE_SORT:
			    sortedList = mergeSortJava(intList);
//                int[] tempSortedNumbers = new int[NUMBERS_ARRAY.length];
//			    mergeSort(NUMBERS_ARRAY, tempSortedNumbers, 0, NUMBERS_ARRAY.length - 1);
				break;
			case BINARY_SEARCH:
//				sortedList = mergeSort(intList);
//			    boolean valueFound = binarySearch(sortedList, 18);
//			    System.out.println("Binary search, value exist = " + valueFound);
				break;
			case SELECTION_SORT:
				selectionSort(NUMBERS_ARRAY);
				break;
		}
		long endTime = System.nanoTime();
		System.out.println(SORT_TYPES[sortType] + " Execution Time = " + (endTime - startTime));
		if (sortedList == null) {
			System.out.println("Sorted List = " + Arrays.toString(NUMBERS_ARRAY));
		} else {
			System.out.println("Sorted List = " + sortedList);
		}
	}
	
}
