package ex18_01_FileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample4_3_try_with_resource_test {

	public static void main(String[] args) {
		char arr[] = new char[64];
		/*
		 * 1. try-with-resource문으로 try() 괄호 안에 선언된 자원은 try 문이 끝날 때 자동으로 close()메서드를
		 * 호출합니다. 2. try-with-resource문에 의해 자동으로 객체의 close()가 호출될 수 있으려면 클래스가
		 * AutoCloseable이라는 인터페이스를 구현한 것이어야 합니다.
		 * 
		 * 형식) try(){ ..... }catch(){
		 * 
		 * }finally{
		 * 
		 * }
		 */
		try (FileReader reader = new FileReader("src/ex18_01_FileReader/1.txt");) {
			while (true) {
				int num = reader.read(arr);
				if (num == -1)
					break;
				System.out.print(new String(arr, 0, num));
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException ioe) {
			System.out.println("파일을 읽을 수 없습니다.");
		} finally {
			System.out.println("\nfinal입니다.");
		}
	}

}
