package ex20_03.client;
import java.net.Socket;

import ex20_03.server.SenderThread;
import ex20_04.client.ReceiverThread;

public class ClientExample4 {
	public static void main(String[] args) {
		System.out.println("여기는 클라이언트 입니다.");
		Socket socket = null;
		try {
			socket = new Socket("192.168.0.30", 9001);
			
			Thread thread1 = new SenderThread(socket);
			Thread thread2 = new ReceiverThread(socket);
			
			thread1.start();
			thread2.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		// thread 여서 실행이 되고 나서 finally 를 읽을 수 있는데 
		// 이때, 클라이언트의 소켓은 닫지 않습니다.

	}
}
