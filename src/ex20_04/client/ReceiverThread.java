package ex20_04.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

// 서버로부터 받는 스레드
public class ReceiverThread extends Thread {
	Socket socket = null;
	public ReceiverThread(Socket socket) {
		this.socket =socket;
	}
	
	@Override
	public void run() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while (!socket.isClosed()) {
				String str = reader.readLine();
				if(str == null)
					break;
				System.out.println(str);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
