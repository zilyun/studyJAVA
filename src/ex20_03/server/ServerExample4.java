package ex20_03.server;

import java.net.ServerSocket;
import java.net.Socket;

import ex20_04.client.ReceiverThread;

// 서버 프로그램
// 실행 순서 : 서버 프로그램 실행 -> 클라이언트 프로그램 실행
public class ServerExample4 {

	public static void main(String[] args) {
		System.out.println("여기는 서버 입니다.");
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(9001);
			socket = serverSocket.accept();
			System.out.println("=========접속 정보===========");
			System.out.println(socket.getInetAddress().getHostAddress() + "에서 접속하였습니다.");
			System.out.println("===========================");
			
			Thread thread1 = new SenderThread(socket);
			Thread thread2 = new ReceiverThread(socket);
			
			thread1.start();
			thread2.start();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
