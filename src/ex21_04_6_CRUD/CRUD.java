package ex21_04_6_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class CRUD {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();

		do {
			int menu = menuselect(sc);
			switch (menu) {
				case 1 -> insert(sc, dao);
				case 2 -> select(sc, dao);
				case 3 -> update(sc, dao);
				case 4 -> delete(sc, dao);
				case 5 -> selectAll(dao);
				case 6 -> {
							sc.close();
							return;
						  }
			}
		} while (true);
	}

	private static void printTitle() {
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", 
				"번호", "이름", "국어", "수학",
				"영어", "총점", "평균", "학점");
		for (int i = 0; i <= 60; i++)
			System.out.printf("=");
		System.out.println();
	}
	
	private static void selectAll(DAO dao) {
		ArrayList<Student> list = dao.selectAll();
		
		if (list.isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			printTitle();
			for (Student s : list) {
				System.out.println(s.toString());
			}
		}
	}
	
	private static boolean isExist(DAO dao, int no) {
		return dao.select(no) != null;
	}

	private static void delete(Scanner sc, DAO dao) {
		System.out.println("삭제할 번호를 입력하세요>");
		int no = inputNumber(sc, 0, 0);
		
		if (isExist(dao, no)) {
			Student student = new Student();
			student.setNo(no);
			
			int result = dao.delete(student);
			
			if(result == 1)
				System.out.println("삭제되었습니다.");
			else
				System.out.println("삭제 중 오류 발생했습니다.");
		} else {
			System.out.println("해당 번호로 조회된 학생이 없습니다.");
		}	
	}
	
	private static void update(Scanner sc, DAO dao) {
		System.out.println("수정할 번호를 입력하세요>");
		int no = inputNumber(sc, 0, 0);
		
		if (isExist(dao, no)) {
			System.out.println("이름을 입력하세요>");
			String name = sc.nextLine();
			
			System.out.println("국어점수를 입력하세요>");
			int kor = inputNumber(sc, 0, 100);
			
			System.out.println("수학점수를 입력하세요>");
			int math = inputNumber(sc, 0, 100);
			
			System.out.println("영어점수를 입력하세요>");
			int eng = inputNumber(sc, 0, 100);
			
			Student student = new Student(name, kor, math, eng);
			student.setNo(no);
			
			int result = dao.update(student);
			
			if(result == 1)
				System.out.println("수정되었습니다.");
			else
				System.out.println("수정 중 오류 발생했습니다.");
		} else {
			System.out.println("해당 번호로 조회된 학생이 없습니다.");
		}
	}

	private static int inputNumber(Scanner sc) {
		return inputNumber(sc, 0, 0);
	}
	
	private static void select(Scanner sc, DAO dao) {
		System.out.println("조회할 번호를 입력하세요>");
		int no = inputNumber(sc);
		Student st = dao.select(no);
		if (st != null) {
			printTitle();
			System.out.println(st.toString());
		} else {
			System.out.println("해당 번호로 조회된 학생이 없습니다.");
		}
	}

	private static void insert(Scanner sc, DAO dao) {
		System.out.println("이름을 입력하세요>");
		String name = sc.nextLine();
		
		System.out.println("국어점수를 입력하세요>");
		int kor = inputNumber(sc, 0, 100);
		
		System.out.println("수학점수를 입력하세요>");
		int math = inputNumber(sc, 0, 100);
		
		System.out.println("영어점수를 입력하세요>");
		int eng = inputNumber(sc, 0, 100);
		
		Student student = new Student(name, kor, math, eng);
		int result = dao.insert(student);
		
		if(result == 1)
			System.out.println("삽입되었습니다.");
		else
			System.out.println("삽입 실패되었습니다.");
	}

	private static int inputNumber(Scanner sc, int start, int end) {
		int input = 0;
		while (true) {
			try {
				input = Integer.parseInt(sc.nextLine());
				if (input <= end && input >= start || start == 0 && end == 0) {
					break;
				} else {
					System.out.println(start + "~" + end + "사이의 숫자를 입력하세요>");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자로 입력하세요>");
			}
		}
		return input;
	}

	private static int menuselect(Scanner sc) {
		String menus[] = { "입력", "조회", "수정", "삭제", "모두조회", "종료" };
		int i = 0;
		for (String m : menus) {
			System.out.println(++i + "." + m);
		}
		System.out.println("메뉴를 선택하세요>");
		return inputNumber(sc, 1, menus.length);
	}

}
