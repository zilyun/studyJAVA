package ex20_04.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 기능) 수신된 첫번째 문자열을 대화명으로 사용합니다.
 * 		연결된 모든 클라이언트에게 "#" + name + "님이 들어오셨습니다."를 보냅니다.
 * 		수신된 메시지 앞에 대화명을 붙여 모든 클라이언트로 송신합니다.
 * 		수신된 메시지가 null 이면 무한반복을 벗어납니다.
 * 		클라이언트가 채팅 종료한 경우		"#" + name + "님이 나가셨습니다." 메시지를 보냅니다.
 * 
 */
// 서버가 클라이언트에게 받고 보내는 스레드
public class PerClientThread extends Thread {
	// ArrayList객체를 여러 스레드가 동시에 사용하게 되면 문제가 발생할 수 있어 안전하게 공유할 수 있는 동기화된 리스트로 만듭니다.
	// 동기화된 리스트로 만들기 위해 java.util.Collections 클래스의 synchronizedList 메서드를 이용합니다.
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());

	private Socket socket = null;
	private PrintWriter writer = null;

	public PerClientThread(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			list.add(writer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		String name = null;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			/*
			 * SenderThread.java 에서 writer.println(name); writer.flush(); 로 보낸 name 을 아래의
			 * 문장에서 읽어옵니다.
			 */

			// 수신된 첫번째 문자열을 대화명으로 사용하기 위해 저장합니다.
			name = reader.readLine();
			sendAll("#" + name + "님이 들어오셨습니다.");
			
			while (!socket.isClosed()) {
				String str = reader.readLine();

				// 소켓의 연결이 끊어지면 null을 리턴합니다.
				if (str == null)
					break;
				
				// 수신된 메시지 앞에 대화명을 붙여서 모든 클라이언트로 송신합니다.
				sendAll(name + ">" + str);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			list.remove(writer);
			
			// 사용자가 채팅을 종료했다는 메시지를 모든 클라이언트로 보냅니다.
			sendAll("#" + name + "님이 나가셨습니다.");
			try {
				reader.close();
			} catch (Exception e) {
				System.out.println("소켓 닫는 중에 에러 발생했습니다.");
			}
		}
	}

	// 서버에 연결된 모든 클라이언트로 똑같은 메시지를 보냅니다.
	synchronized private void sendAll(String str) {
		for (PrintWriter writer : list) {
			writer.println(str);
			writer.flush();
		}
		// 추가한 부분 - 서버 화면에 출력되도록 합니다.
		System.out.println(str);
	}
}
