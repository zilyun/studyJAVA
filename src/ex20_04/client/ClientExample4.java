package ex20_04.client;
import java.net.Socket;

import ex20_04.server.SenderThread;

public class ClientExample4 {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("대화명을 입력하세요.");
		}
		
		try {
			System.out.println("==================");
			System.out.println("나의 대화명은 " + args[0] + "입니다.");
			System.out.println("==================");
			
			// 서버와 연결을 맺습니다.
			Socket socket = new Socket("192.168.0.6", 9002);
			
			// 메시지 송신 스레드 생성 
			Thread thread1 = new SenderThread(socket, args[0]);
			
			// 메시지 수신 스레드 생성 
			Thread thread2 = new ReceiverThread(socket);
			
			// 스레드 시작
			thread1.start();
			thread2.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		// thread 여서 실행이 되고 나서 finally 를 읽을 수 있는데 
		// 이때, 클라이언트의 소켓은 닫지 않습니다.
	}
}
