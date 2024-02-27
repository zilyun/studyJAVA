package ex21_04_7_Board;

public class Board {
	private int board_num;
	private String board_name;
	private String board_pass;
	private String board_subject;
	private String board_content;
	private String board_file;
	private String board_original;
	private int board_RE_REF;
	private int board_RE_LEV;
	private int board_RE_SEQ;
	private int board_readcount;
	private String board_date;

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	public String getBoard_pass() {
		return board_pass;
	}

	public void setBoard_pass(String board_pass) {
		this.board_pass = board_pass;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_file() {
		return board_file;
	}

	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}

	public String getBoard_original() {
		return board_original;
	}

	public void setBoard_original(String board_original) {
		this.board_original = board_original;
	}

	public int getBoard_RE_REF() {
		return board_RE_REF;
	}

	public void setBoard_RE_REF(int board_RE_REF) {
		this.board_RE_REF = board_RE_REF;
	}

	public int getBoard_RE_LEV() {
		return board_RE_LEV;
	}

	public void setBoard_RE_LEV(int board_RE_LEV) {
		this.board_RE_LEV = board_RE_LEV;
	}

	public int getBoard_RE_SEQ() {
		return board_RE_SEQ;
	}

	public void setBoard_RE_SEQ(int board_RE_SEQ) {
		this.board_RE_SEQ = board_RE_SEQ;
	}

	public int getBoard_readcount() {
		return board_readcount;
	}

	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	public String toString() {
		String re = "";
		if (board_RE_LEV > 0) {
			//for (int i = 0; i < board_RE_LEV * 2; i++)
			//	re += " ";
			re += "re:";
		}

		return String.format("%s\t%s\t%-16s\t%-16s\t%s\t%s\t%s\t%s", 
				board_num, board_name, (re + board_subject),
				board_content, board_RE_REF, board_RE_LEV, 
				board_RE_SEQ, board_date.substring(0, 10));
	}
}
