package ex21_04_1_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

// emp 의 모든 정보를 조회하는 클래스를 통해 가져온 데이터를 출력합니다.
public class CRUD {
	// CRUD : 컴퓨터 소프트웨어가 가지는 기본적인 데이터 처리 기능
	// C(Create-insert), R(Read_select)
	// U(Update-update), D(Delete-delete)
	static String menus[] = { "사원번호", "사원이름", "직급", "매니저", 
						"입사일자", "급여", "커미션", "부서번호", "종료" };

	public static void main(String[] args) {
		// 선택 조회
		Scanner sc = new Scanner(System.in);
		int menu = menuselect(sc);
		// System.out.println(menu);
		String searchData = input(menu, sc);
		select(menus[menu - 1], searchData);
		sc.close();
	}
	
	private static void select(String menu, String searchData) {
		DAO dao = new DAO();
		ArrayList<Emp> list = dao.select(menu, searchData);

		if (list.isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			System.out.printf("%s\t%s\t%s\t\t%s\t%s\t\t%s\t%s\t%s\t\n", 
					menus[0], menus[1], menus[2], menus[3],
					menus[4], menus[5], menus[6], menus[7]);
			for (int i = 0; i <= 80; i++)
				System.out.printf("=");
			System.out.println();
			for (Emp e : list) {
				System.out.println(e.toString());
			}
		}
	}

	private static String searchData(int menu, Scanner sc) {
		System.out.println("조회할 " + menus[menu - 1] + "를(을) 입력하세요>");
		return sc.next();
	}
	
	private static String input(int menu, Scanner sc) {
		String search_word = "";
		if (menu != 9)
			search_word = searchData(menu, sc);
		return search_word;
	}

	private static int inputNumber(Scanner sc) {
		int input = 0;
		int lowNumber = 1;
		int hiNumber = menus.length; // 9
		while (true) {
			try {
				input = Integer.parseInt(sc.nextLine());
				if (input <= hiNumber && input >= lowNumber) {
					break;
				} else {
					System.out.println(lowNumber + "~" + hiNumber + "사이의 숫자를 입력하세요>");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자로 입력하세요>");
			}
		}
		return input;
	}
	
	private static int menuselect(Scanner sc) {
		int i = 0;
		for (String m : menus) {
			System.out.println(++i + "." + m);
		}
		System.out.println("조회할 컬럼을 선택하세요>");
		return inputNumber(sc);
	}
}
