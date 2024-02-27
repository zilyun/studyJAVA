package ex18_06_BufferdReader3_test3_no_rank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BufferdReaderExample3 {

	public static void main(String[] args) {
		input();
	}

	static void input() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new FileReader("src/ex18_06_BufferdReader3_test3_no_rank/jumsu.txt"));
			ArrayList<Student> list = new ArrayList<Student>();
			while (true) {
				String str = reader.readLine();
				if(str == null)
					break;
				String[] sep = str.split(" ");
				list.add(new Student(
						sep[0],
						Integer.parseInt(sep[1]),
						Integer.parseInt(sep[1]),
						Integer.parseInt(sep[1])
						));
			}
			
			// print(list);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
