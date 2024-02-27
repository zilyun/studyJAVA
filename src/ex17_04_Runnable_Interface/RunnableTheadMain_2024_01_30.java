package ex17_04_Runnable_Interface;

public class RunnableTheadMain_2024_01_30 {

	public static void main(String[] args) {
		RunnableThread_2024_01_30 runnable = new RunnableThread_2024_01_30();
		Thread thread = new Thread(runnable);
		thread.start();
	}

}
