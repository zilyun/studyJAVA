
package ex19_03_SerialVersionUID_no;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputExample {
	public static void main(String[] args) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("output10.dat"));
			while (true) {
				Rectangle rect = (Rectangle) in.readObject();
				System.out.println("***역직렬화 합니다.***");
				System.out.println(rect.toString());
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("해당 클래스가 존재하지 않습니다.");
		} catch (EOFException eofe) {
			System.out.println("끝");
		} catch (IOException ioe) {
			System.out.println("파일을 읽을 수 없습니다.");
		} finally {
			try {
				if (in != null)
					in.close(); // 3. 파일로 닫습니다.
			} catch (IOException e) {
				System.out.println("파일로 닫는 중 오류 발생했습니다.");
			}
		}
	}
}
