package Samples;

public class Utils {
	public static final int TIME_INTERVAL = 1000;
	public static void simulateTimeComplexity() {
		try {
			Thread.sleep(TIME_INTERVAL);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void simulateTimeComplexity(int counter) {
		for (int i = 0; i < counter; i++) {
			simulateTimeComplexity();
		}
	}

	public static void swap(int[] numbers, int firstIndex, int secondIndex) {
		int temp = numbers[firstIndex];
		numbers[firstIndex] = numbers[secondIndex];
		numbers[secondIndex] = temp;
	}


	public static void printMatrix(int[][] matrix) {
		System.out.println();
		for (int i = 0; i < matrix.length ; i++) {
			for (int j = 0; j < matrix[0].length ; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
