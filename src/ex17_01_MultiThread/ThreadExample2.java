package ex17_01_MultiThread;

import java.util.Scanner;

public class ThreadExample2 {

	public static void main(String[] args) {
		DigitThread th1 = new DigitThread();
		th1.start();
		
		System.out.println("아무거나 입력하세요~");
		Scanner sc = new Scanner(System.in);
		System.out.println("입력하신 내용은 \""+ sc.nextLine() + "\" 입니다.");
		
		sc.close();
	}

}
