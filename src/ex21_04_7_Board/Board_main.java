package ex21_04_7_Board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board_main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BoardDAO_seq dao = new BoardDAO_seq();

		do {
			int menu = menuselect(sc);
			switch (menu) {
			case 1 -> insert(sc, dao);
			case 2 -> update(sc, dao);
			case 3 -> reply(sc, dao);
			case 4 -> delete(sc, dao);
			case 5 -> selectAll(dao);
			case 6 -> selectPage(sc, dao);
			case 7 -> {
					sc.close();
					return;
				}
			}
		} while (true);
	}

	private static void selectPage(Scanner sc, BoardDAO_seq dao) {
		int listcount = dao.count();
		
		if(listcount > 0) {
			System.out.println("한 페이지에 보여줄 목록을 입력하세요(1~10)>");
			int limit = inputNumber(sc, 1, 10);
			int maxpage = (listcount - 1) / limit + 1;
			System.out.println("선택할 페이지를 입력하세요(1~" + maxpage + ")>");
			int page = inputNumber(sc, 1, maxpage);
			
			ArrayList<Board> arrs = dao.getBoardList(page, limit);
			
			System.out.println("글의 총 갯수 : " + listcount);
			printTitle();
			for (Board board : arrs) {
				System.out.println(board.toString());
			}

		} else if (listcount <= 0) {
			System.out.println("테이블에 데이터가 없습니다.");
		}
	}

	private static void reply(Scanner sc, BoardDAO_seq dao) {
		System.out.println("답변을 달 글 번호를 입력하세요>");
		int no = inputNumber(sc);
		Board board = select(dao, no);

		if (board != null) {
			System.out.println("글쓴이>");
			board.setBoard_name(sc.nextLine());

			System.out.println("제목>");
			board.setBoard_subject(sc.nextLine());

			System.out.println("글 내용>");
			board.setBoard_content(sc.nextLine());

			System.out.println("비밀번호>");
			board.setBoard_pass(sc.nextLine());

			int result = dao.boardReply(board);
			if (result != 0)
				System.out.println("답변 달기 성공");
			else
				System.out.println("답변 달기 실패");
		} else {
			System.out.println("해당 번호로 조회된 글이 없습니다.");
		}

	}

	private static void printTitle() {
		System.out.printf("%s\t%s\t%s\t\t\t%s\t\t\t%s\t%s\t%s\t%s\t\n", "글번호", "작성자", "제목", "내용", "ref", "lev", "seq",
				"date");
		for (int i = 0; i <= 100; i++)
			System.out.printf("=");
		System.out.println();
	}

	private static void selectAll(BoardDAO_seq dao) {

		// 1 - 페이지(page)
		// 10 - 페이지 당 목록의 수(limit)
		ArrayList<Board> list = dao.getBoardList(1, 10);

		if (list.isEmpty()) {
			System.out.println("테이블에 데이터가 없습니다.");
		} else {
			printTitle();
			for (Board board : list) {
				System.out.println(board.toString());
			}
		}
	}

	private static void delete(Scanner sc, BoardDAO_seq dao) {
		System.out.println("삭제할 번호를 입력하세요>");
		int no = inputNumber(sc, 0, 0);
		Board board = select(dao, no);
		
		if (board != null) {
			System.out.println("비밀번호>");
			String pass = sc.nextLine();
			
			if (pass.equals(board.getBoard_pass())) {
				int count = dao.boardDelete(board);
				System.out.println(count + " 개의 글이 삭제되었습니다.");
			} else {
				System.out.println("비밀번호가 다릅니다.");
				
			}
		} else {
			System.out.println("해당 번호로 조회된 글이 없습니다.");
		}	
	}
	
	private static void update(Scanner sc, BoardDAO_seq dao) {
		System.out.println("수정할 글 번호를 입력하세요>");
		int no = inputNumber(sc);
		Board board = select(dao, no);

		if (board != null) {
			System.out.println("제목>");
			board.setBoard_subject(sc.nextLine());

			System.out.println("글 내용>");
			board.setBoard_content(sc.nextLine());

			System.out.println("비밀번호>");
			String pass = sc.nextLine();

			// 지금 입력한 비밀번호와 DB에 저장된 비밀번호 비교하기
			if (pass.equals(board.getBoard_pass())) {
				int result = dao.boardModify(board);

				if (result == 1)
					System.out.println("수정에 성공했습니다.");
				else
					System.out.println("수정에 실패했습니다.");
			} else {
				System.out.println("비밀번호가 다릅니다.");
			}
		} else {
			System.out.println("해당 번호로 조회된 글이 없습니다.");
		}
	}

	private static int inputNumber(Scanner sc) {
		return inputNumber(sc, 0, 0);
	}

	private static Board select(BoardDAO_seq dao, int no) {
		// System.out.println("조회할 글 번호를 입력하세요>");
		// int no = inputNumber(sc);
		Board board = dao.getDetail(no);
		if (board != null) {
			printTitle();
			System.out.println(board.toString());
		} else {
			System.out.println("해당 번호로 조회된 글이 없습니다.");
		}
		return board; // 수정, 삭제 시에는 비밀번호 확인을 위해 필요
						// 답변의 경우 원문글(선택한 글)의
						// BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ 값이 필요
	}

	private static void insert(Scanner sc, BoardDAO_seq dao) {
		Board board = new Board();
		System.out.println("작성자>");
		board.setBoard_name(sc.nextLine());

		System.out.println("비밀번호>");
		board.setBoard_pass(sc.nextLine());

		System.out.println("제목>");
		board.setBoard_subject(sc.nextLine());

		System.out.println("글 내용>");
		board.setBoard_content(sc.nextLine());

		if (dao.boardInsert(board) == 1)
			System.out.println("글이 작성되었습니다.");
		else
			System.out.println("오류가 발생되었습니다.");
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
		String menus[] = { "글쓰기", "수정", "답변달기", "글삭제", "조회", "페이지 선정", "종료" };
		int i = 0;
		for (String m : menus) {
			System.out.println(++i + "." + m);
		}
		System.out.println("메뉴를 선택하세요>");
		return inputNumber(sc, 1, menus.length);
	}
}
