package ex21_04_7_Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO_seq {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Connection connectionJDBC() {
		// 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "board", "1234");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}

		return conn;
	}

	public void rsClose(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
	}

	public void pstmtClose(PreparedStatement pstmt) {
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
	}

	public void connClose(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
	}
	
	public int count() {
		int count = 0;
		String count_sql = "select count(*) from board";
		
		try {
			conn = connectionJDBC();
			pstmt = conn.prepareStatement(count_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("count() 에러 : " + se);
		} finally {
			// 순서대로 닫을 것
			pstmtClose(pstmt);
			connClose(conn);
		}
		
		return count;
	}

	public int boardDelete (Board board) {
		int count = -1;
		
		try {
			conn = connectionJDBC();
			
			String delete_sql = "delete from board where BOARD_NUM in "
							  + "(select BOARD_NUM " 
							  + " from board "
							  + " where BOARD_RE_REF = ? "
							  + " and BOARD_RE_LEV  >= ? "
							  + " and BOARD_RE_SEQ between ? and " 
							  + " NVL((select MIN(BOARD_RE_SEQ) - 1 " 
							  + "	   from board " 
							  + "	   where BOARD_RE_REF = ? " 
							  + "	   and BOARD_RE_LEV   = ? " 
							  + "      and BOARD_RE_SEQ   > ?), " 
							  + "	  (select MAX(BOARD_RE_SEQ) "
							  + "      from board "
							  + "	   where BOARD_RE_REF = ?)))";
			
			pstmt = conn.prepareStatement(delete_sql);
			pstmt.setInt(1, board.getBoard_RE_REF());
			pstmt.setInt(2, board.getBoard_RE_LEV());
			pstmt.setInt(3, board.getBoard_RE_SEQ());
			pstmt.setInt(4, board.getBoard_RE_REF());
			pstmt.setInt(5, board.getBoard_RE_LEV());
			pstmt.setInt(6, board.getBoard_RE_SEQ());
			pstmt.setInt(7, board.getBoard_RE_REF());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			pstmtClose(pstmt);
			connClose(conn);
		}
		
		return count;
	}
	

	public int boardReply (Board board) {
		int result = 0;
		
		/* 답변을 할 원문 글 그룹 번호
		 * 답변을 달게 되면 답변 글은 이 번호와 같은 관련글 번호를 갖게 처리되면서
		 * 같은 그룹에 속하게 됩니다. 글 목록에서 보여줄 때 하나의 그룹으로 묶여서 출력됩니다.
		 * */
		int re_ref = board.getBoard_RE_REF();
		
		/* 답글의 깊이를 의미합니다.
		 * 원문에 대한 답글이 출력될 때 한번 들여쓰기 처리가 되고 답글에 대한 답글은 들여쓰기가 두 번 처리되게 
		 * 합니다. 원문의 경우에는 이 값이 0이고 원문의 답글은 1, 답글의 답글은 2가 됩니다.
		 * */
		int re_lev = board.getBoard_RE_LEV();
		
		// 같은 관련 글 중에서 해당 글이 출력되는 순서입니다.
		int re_seq = board.getBoard_RE_SEQ();
		
		// BOARD_RE_REF, BOARD_RE_SEQ 값을 확인하여 원문 글에 다른 답글이 있으면 
		// 다른 답글들의 BOARD_RE_SEQ 값을 1씩 증가시킵니다.
		// 현재 글을 다른 답글보다 앞에 출력되게 하기 위해서 입니다. 
		String sql1 = " update board " 
			     + "set    BOARD_RE_SEQ = BOARD_RE_SEQ + 1 " 
				 + "where  BOARD_RE_REF = ? "
			  	 + "and    BOARD_RE_SEQ > ?";
		String sql2 = "insert into board " 
			     + "(BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
				 + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," 
			     + " BOARD_RE_LEV, BOARD_RE_SEQ,"
				 + " BOARD_READCOUNT,BOARD_DATE) " 
			     + "values(board_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";

		try {
				conn = connectionJDBC();
				// 트랜잭션을 이용하기 위해서 setAutoCommit을 false 로 설정합니다.
				conn.setAutoCommit(false);
				//----------- 1번 작업 시작 ------------//
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, re_ref); 
				pstmt.setInt(2, re_seq); 
				pstmt.executeUpdate();
				pstmt.close();
				//----------- 2번 작업 시작 ------------//
				// 등록할 답변 글의 BOARD_RE_LEV, BOARD_RE_SEQ 값을 원문 글보다 1씩 증가시킵니다.
				++re_seq;
				++re_lev;
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, board.getBoard_name());
				pstmt.setString(2, board.getBoard_pass());
				pstmt.setString(3, board.getBoard_subject());
				pstmt.setString(4, board.getBoard_content());
				pstmt.setString(5, ""); // 답변에는 파일을 업로드하지 않습니다.
				pstmt.setInt(6, re_ref); // BOARD_RE_LEV 필드
				pstmt.setInt(7, re_lev); // BOARD_RE_SEQ 필드
				pstmt.setInt(8, re_seq); // BOARD_READCOUNT 필드
				pstmt.setInt(9, 0); // BOARD_READCOUNT(조회수)는 0
				result = pstmt.executeUpdate();
				conn.commit();
				
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println("boardReply() 에러 : " + se);
				try {
					if (conn != null) {
						conn.rollback();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} finally {
				// 순서대로 닫을 것
				pstmtClose(pstmt);
				connClose(conn);
			}
		 
		return result;
	}
	
	public int boardModify(Board modifyBoard) {
		int result = -1;
		String update_sql = " UPDATE board SET " 
						  + " BOARD_SUBJECT = ?, BOARD_CONTENT = ? "
						  + " WHERE BOARD_NUM = ?";
		try {
			conn = connectionJDBC();
			pstmt = conn.prepareStatement(update_sql);
			pstmt.setString(1, modifyBoard.getBoard_subject());
			pstmt.setString(2, modifyBoard.getBoard_content());
			pstmt.setInt(3, modifyBoard.getBoard_num());
			result = pstmt.executeUpdate();

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			pstmtClose(pstmt);
			connClose(conn);
		}

		return result;
	}

	public Board getDetail(int no) {
		Board board = null;
		String select_sql = "select * from board where BOARD_NUM = ?";
		
		try {
			conn = connectionJDBC();
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new Board();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_pass(rs.getString("BOARD_PASS"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				// board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBoard_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}

		return board;
	}

	public int boardInsert(Board InsertBoard) {
		int result = 0;
		String student_insert_sql = "insert into " 
				+ "board(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, "
				+ "		 BOARD_CONTENT, BOARD_RE_REF, " 
				+ "		 BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, "
				+ "		 BOARD_DATE) " 
				+ "values(BOARD_SEQ.NEXTVAL, ?, ?, ?, " 
				+ "		?, BOARD_SEQ.NEXTVAL, "
				+ "		?, ?, ?, " + "		sysdate)";

		try {
			conn = connectionJDBC();
			pstmt = conn.prepareStatement(student_insert_sql);
			// 새로운 글을 등록하는 부분
			pstmt.setString(1, InsertBoard.getBoard_name());
			pstmt.setString(2, InsertBoard.getBoard_pass());
			pstmt.setString(3, InsertBoard.getBoard_subject());
			pstmt.setString(4, InsertBoard.getBoard_content());
			// 원문의 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0입니다.
			pstmt.setInt(5, 0); // BOARD_RE_LEV 필드
			pstmt.setInt(6, 0); // BOARD_RE_SEQ 필드
			pstmt.setInt(7, 0); // BOARD_READCOUNT 필드
			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("boardInsert() 에러: " + ex);
			ex.printStackTrace();
		} finally {
			// 순서대로 닫을 것
			pstmtClose(pstmt);
			connClose(conn);
		}

		return result;
	}

	public ArrayList<Board> getBoardList(int page, int limit) {
		ArrayList<Board> list = new ArrayList<Board>();
		int startRow = (page - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		String select_sql = " select * " + " from " + " 	(select rownum rnum, b.* " + "  	 from (select * "
						  + " 		   from board " + " 	  	   order by BOARD_RE_REF desc, BOARD_RE_SEQ asc) b "
						  + " where rownum <= ?)"   + " where rnum between ? and ?";
		
		try {
			conn = connectionJDBC();
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_pass(rs.getString("BOARD_PASS"));//비밀번호 확인시 사용
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				// board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBoard_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				// board.setBoard_readcount(rs.getString("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				list.add(board);
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}
		return list;
	}

	
}
