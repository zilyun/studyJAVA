package ex20_04.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 서버에게 보내는 스레드
public class SenderThread extends Thread {
	Socket socket = null;
	String name = null;

	public SenderThread(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}

	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			writer = new PrintWriter(socket.getOutputStream());

			// 추가
			// 제일 먼저 서버로 대화명을 송신합니다.
			writer.println(name);
			writer.flush();

			// 키보드로 입력된 메시지를 서버로 송신합니다.
			while (!socket.isClosed()) {
				String str = reader.readLine();
				if (str.equals("bye"))
					break;
				writer.println(str);
				writer.flush();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
