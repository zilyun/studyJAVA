package ex18_10_File;

import java.io.File;

public class FileExample2_3 {
	public static void main(String[] args) {
		String dir = "D:\\newDirectory\\newFile.txt";

		File f3 = new File(dir);
		if (f3.exists()) { 
			if (f3.delete()) { // 파일 삭제
				System.out.println(f3.getPath() + " 삭제했습니다.");
			} else {
				System.out.println(f3.getPath() + " 삭제 하지 못했습니다.");
			}
		} else { 
			System.out.println(f3.getPath() + "에 삭제하고자 하는 파일이 없습니다.");
		}
	}
}
