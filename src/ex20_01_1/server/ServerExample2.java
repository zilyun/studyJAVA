package ex20_01_1.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample2 {

	public static void main(String[] args) {
		System.out.println("여기는 서버 입니다. ~");
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(9001);
			socket = serverSocket.accept();
			/*
			 * InputStreamReader 클래스 :
			 * 바이트 스트림을 문자 스트림으로 바꾸어주는 클래스
			 * 이 클래스의 생성자에 소켓으로부터 얻은 InputStream 객체를 넘겨주면
			 * 네트워크로 수신된 데이터를 문자 스트림 형태로 읽을 수 있는 
			 * InputStreamReader 객체가 생성됩니다.
			 * */
			
			BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
					
			/*
			 * PrintWriter 클래스를 이용하여 문자 스트림을 바이트 스트림으로 바꾸어서 
			 * 출력하기 위해서는 이 클래스의 생성자에 OutputStream 타입의 객체를 파라미터로 
			 * 넘겨주어야 합니다.
			 * */
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			// getBytes 메소드는 문자열을 바이트 배열로 만드는 메소드입니다.
			writer.println("Hello, Client World1");
			writer.flush();
			
			// 데이터를 수신합니다.
			String str = reader.readLine();
			// 수신된 데이터를 출력합니다.
			System.out.println(str);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
