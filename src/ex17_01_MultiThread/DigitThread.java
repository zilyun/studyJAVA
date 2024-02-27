package ex17_01_MultiThread;

public class DigitThread extends Thread {
	public void run() {
		for (int cnt = 0; cnt < 10; cnt++) {
			System.out.print(cnt);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		// System.out.println("스레드 이름2: " + Thread.currentThread().getName());
	}
}
