package ex17_01_MultiThread;

// 네 개의 스레드로 실행되는 멀티스레드 프로그램
// main, thread1, thread2, thread3
public class MultiThreadExample2 {
	public static void main(String[] args) {
		Thread thread1 = new DigitThread();
		Thread thread2 = new DigitThread();
		Thread thread3 = new AlphabetThread();
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
