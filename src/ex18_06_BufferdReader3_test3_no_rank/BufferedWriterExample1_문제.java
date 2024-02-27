package ex18_06_BufferdReader3_test3_no_rank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample1_문제 {
	public static void main(String[] args) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("src/ex18_06_BufferdReader3_test3_no_rank/output.txt");
			
			// 두 번째 인자는 버퍼 사이지를 의미 - 버어픠 5개의 문자를 저장할 수 있음
			// 두 번째 인자가 생략된 경우 defaultCharBufferSize = 8192로 설정
			bw = new BufferedWriter(fw, 5);
			
			char arr[] = {'내', '꺼', '인', '1', '듯', 
						  '2', '내', '꺼', '3', '아', 
						  '닌', '4', '내', '꺼', '5', 
						  '같', '은', '6', '너'
						  };
			
			for (int cnt = 0; cnt< arr.length; cnt++)
				bw.write(arr[cnt]);
			/*
			 * 버퍼가 다 차기 전까지는 파일에 실제로 데이터를 쓰지 않습니다.
			 * */
			
			// 버퍼에 있는 데이터를 파일에 즉시 출력하도록 하는 메소드입니다.
			bw.flush();
		} catch (IOException e) {
			System.out.println("파일로 출력할 수 없습니다.");
		} finally {
			try {
				if(fw != null)
					fw.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}	
	}
}
