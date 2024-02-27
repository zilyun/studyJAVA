package ex18_10_File;

import java.io.File;

// File 클래스를 디렉토리 생성
// 디렉토리 생성에 실패했습니다.
public class FileExample2_5 {
	public static void main(String[] args) {
		String dir = "D:\\newDirectory\\test";
		/*
		 * mkdirs() 메서드는 현재 디렉토리와 그 부모 디렉토리를 모두 만듬.
		 * */
		File f = new File(dir);
		if (!f.exists()) { 
			if (f.mkdirs()) { // 디렉토리 생성 - 생성되면 true, 실패하면 false
				System.out.println("새로 만든 디렉톨리 이름 : " + f.getPath());
			} else {
				System.out.println("디렉토리 생성에 실패했습니다.");
			}
		} else { 
			System.out.println("이미 있는 디렉토리 이름 : " + f.getPath());
		}
	}
}
