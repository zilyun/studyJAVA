package ex19_02_Serialization_error;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputExample1 {
	public static void main(String[] args) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("output2.dat"));
			
			out.writeObject(new GoodsStock("70101", 100));
			out.writeObject(new GoodsStock("70102", 50));
			out.writeObject(new GoodsStock("70103", 200));
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