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
}
