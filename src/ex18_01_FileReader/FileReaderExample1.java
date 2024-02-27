package ex18_01_FileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample1 {

	public static void main(String[] args) {
		// 1. 파일을 엽니다.
		/*
		 * 생성자 인자값으로 파일의 위치 지정 지정 방법 : 상대 경로와 절대 경로 지정 1) 상대 경로 : 프로젝트 경로를 기준으로 차례대로 파일의
		 * 위치를 지정 2) 절대 경로 : 드라이브명부터 차례대로 파일의 위치를 지정 예) D:/작업폴더/프로젝트/poem.txt 의미
		 */
		FileReader reader = null;
		try {
			reader = new FileReader("./src/ex18_01_FileReader/poem.txt");

			// 절대 경로 지정
			// FileReader reader =
			// new FileReader("D:/workspace_java/java/poem.txt");

			// 2. 파일을 읽어서 처리합니다.
			while (true) {
				// read() : 파일에 있는 문자 하나를 읽어서 리턴하는 메소드
				int data = reader.read();
				if (data == 1)
					break;
				char ch = (char) data;
				System.out.print(ch);
				Thread.sleep(100);
			}
		} catch (FileNotFoundException e) { // FileReader() 생성자가 발생한 Exception 처리
			// e.printStackTrace();
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException e) { // read() 메소드가 발생한 예외 처리
			// e.printStackTrace();
			System.out.println("파일이 읽을 수 없습니다.");
		} catch (InterruptedException e) {
			System.out.println("시스템에서 의도치 않은 예외가 발생했습니다.");
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
				System.out.println("IOException");
			}
		}
	}
}
