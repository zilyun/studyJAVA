package ex19_03_SerialVersionUID_no;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputExample {
	public static void main(String[] args) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("output10.dat"));
			Rectangle obj = new Rectangle(100, 200);
			out.writeObject(obj);
			System.out.println("***직렬화 합니다.***");
			System.out.println(obj); // 객체를 직렬화하는 부분
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("파일로 출력할 수 없습니다.");
		} finally {
			try {
				if(out != null)
					out.close(); 
			} catch (IOException e) {
				System.out.println("파일로 닫는 중 오류 발생했습니다.");
			}
		}
	}
}