package ex17_01_MultiThread;
/* 싱글 쓰레드보다 멀티 쓰레드를 사용하면 더 효율적인 경우
 * 1. 데이터를 입력받는 작업 
 * 	- 입력받는 동안 대기하면서 아무 작업을 안하는 건 비효율적
 * 2. 네트워크로 파일을 주고 받는 작업
 * 3. 프린터로 파일을 출력하는 작업 등 외부기기와의 입출력을 필요로 하는 경우
 * 
 * TheadExample1.java 는 하나의 쓰레드로 사용자의 입력을 받는 작업과 화면에 숫자를 
 * 출력하는 작업을 처리하기 때문에 사용자가 입력을 마치기 전까지는 
 * 화면에 숫자가 출력되지 않는다.
 */
import java.util.Scanner;
public class ThreadExample1 {

	public static void main(String[] args) {
		System.out.println("아무거나 입력하세요~");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("입력하신 내용은 \""+sc.nextLine()+"\" 입니다.");
		
		for(int i = 0; i < 10; i++) {
			System.out.println(i);
		}
		sc.close();
	}

}
