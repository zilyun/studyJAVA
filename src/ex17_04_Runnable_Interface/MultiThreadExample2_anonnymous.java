package ex17_04_Runnable_Interface;

public class MultiThreadExample2_anonnymous {
	public static void main(String[] args) {
		// SmallLetters small = new SmallLetters();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (char ch = 'a'; ch <= 'z'; ch++) {
					System.out.print(ch);
				}
			}
		});
		
		thread.start();

		// - start() 설명 : JVM이 스레드의 run() 메소드를 호출합니다.
		// start method는 java.lang.Thread 클래스의 메소드입니다.
		// 새로 생성한 Thread를 start 하면 Runnable 상태가 됩니다.
		// therad.run(); 이렇게 호출하지 않습니다.
		// 모든 스레드가 종료되어야 프로그램이 종료된다.

		char arr1[] = 
			{'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};

		for(char ch : arr1) {
			System.out.print(ch);
		}
		
		//System.out.println("스레드 이름 : " + Thread.currentThread().getName());
	}
}