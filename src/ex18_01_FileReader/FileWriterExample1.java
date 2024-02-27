package ex18_01_FileReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample1 {

	public static void main(String[] args) {
		FileWriter writer = null;
		try {
			// 생성자의 매개변수가 열 파일의 이름을 의미합니다.
			// output.txt 파일이 없는 경우는 파일 생성 후 파일이 열립니다.
			// 만약 이름이 있는 경우 기존 파일의 내용이 지워집니다.
			writer = new FileWriter("output4.txt");
			
			char arr[] = {'내', '꺼', '인', ' ', '듯', ' ', '내', '꺼', ' ', 
					'아', '닌', ' ', '내', '꺼', ' ', '같', '은', ' ', '너'};
			
			for (int cnt = 0; cnt < arr.length; cnt++) {
				writer.write(arr[cnt]);
			}
		} catch (FileNotFoundException e) { // FileReader() 생성자가 발생한 Exception 처리
			// e.printStackTrace();
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException e) { // read() 메소드가 발생한 예외 처리
			// e.printStackTrace();
			System.out.println("파일이 읽을 수 없습니다.");
		} finally {
			try {
				if(writer != null) 
					writer.close();
			} catch (IOException e) {
				System.out.println("파일이 정상적으로 닫히지 않았습니다.");
			}
		}
	}

}
