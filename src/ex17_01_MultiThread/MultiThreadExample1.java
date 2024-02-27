package ex17_01_MultiThread;

public class MultiThreadExample1 {
	public static void main(String[] args) {
		Thread thread = new DigitThread();
		thread.start();

		// - start() 설명 : JVM이 스레드의 run() 메소드를 호출합니다.
		// start method는 java.lang.Thread 클래스의 메소드입니다.
		// 새로 생성한 Thread를 start 하면 Runnable 상태가 됩니다.
		// therad.run(); 이렇게 호출하지 않습니다.
		// 모든 스레드가 종료되어야 프로그램이 종료된다.

		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.print(ch);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

		//System.out.println("스레드 이름 : " + Thread.currentThread().getName());
	}
}