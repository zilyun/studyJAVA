package ex18_01_FileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample4_1 {

	public static void main(String[] args) {
		FileReader reader = null;
		char arr[] = new char[64];
		
		try {
			reader = new FileReader("./src/ex18_01_FileReader/거위의 꿈.txt");

			// 절대 경로 지정
			// FileReader reader =
			// new FileReader("D:/workspace_java/java/poem.txt");

			// 2. 파일을 읽어서 처리합니다.
			while (true) {
				// read() : 파일에 있는 문자 하나를 읽어서 리턴하는 메소드
				// arr의 배열 크기 만큼 문자를 읽어 arr에 저장한 후 읽은 개수를 반환합니다.
				int num = reader.read(arr);
				if (num == -1) // 더 이상 읽을 문자가 없을 경우 -1을 리턴합니다.
					break;
				
				for (int cnt = 0; cnt < num; cnt++) {
					System.out.printf("%c", arr[cnt]);
				}
			}
		} catch (FileNotFoundException e) { // FileReader() 생성자가 발생한 Exception 처리
			// e.printStackTrace();
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException e) { // read() 메소드가 발생한 예외 처리
			// e.printStackTrace();
			System.out.println("파일이 읽을 수 없습니다.");
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
