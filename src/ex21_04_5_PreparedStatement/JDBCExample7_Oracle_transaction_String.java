package ex21_04_5_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 처음 실행 - db 반영됨
// 두번 실행 - db 취소됨 (code varchar2(5) primary key - 같은 코드 값을 두 번 삽입해서 오류 발생)
public class JDBCExample7_Oracle_transaction_String {
	public static void main(String[] args) {
		// transaction : 논리적인 작업 단위
		// insert, delete, update 등의 작업들을 하나의 논리적인 작업 단위로
		// 묶어서 쿼리 실행시 모든 작업이 정상 처리된 경우에는 commit 을 실행해서 
		// db 에 반영하고 쿼리 실행 중 하나라도 정상처리 되지 않는 경우
		// rollback 을 실행해서 작업 단위 내의 모든 작업을 취소한다.
		
		String sql1 = "insert into goodsinfo "
			    + "values (?, ?, ?, ?)";
		
		String sql2 = "update goodsinfo "
					+ "set price = ? "
					+ "where code = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			
			// autoCommit 기능 비활성화 시키기
			conn.setAutoCommit(false);
			
			//----------- 1번 작업 시작 ------------//
			pstmt = conn.prepareStatement(sql1);
			
			// 쿼리의 ?에 대체될 실제값 지정하기
			pstmt.setString(1, "10006"); 
			pstmt.setString(2, "공기청정기");
			pstmt.setInt(3, 500000);
			pstmt.setString(4, "코웨이");
			int result1 = pstmt.executeUpdate();
			pstmt.close();
			//----------- 1번 작업 종료 ------------//
			
			//----------- 2번 작업 시작 ------------//
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, 100); 
			pstmt.setString(2, "10001");
			int result2 = pstmt.executeUpdate();
			//----------- 2번 작업 종료 ------------//
			
			// 쿼리가 정상적으로 실행된 경우 db 에 반영
			if(result1 == 1 && result2 == 1) {
				conn.commit();
				System.out.println("db에 반영됨 . . . .");
			} else {
				conn.rollback();
				System.out.println("db 반영이 취소됨 ......");
			}
			
			// autoCommit 기능 활성화 시키기
			conn.setAutoCommit(true);
			
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			try {
				conn.rollback();
				System.out.println("db 반영이 취소됨");
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close(); // Statement 객체가 닫히면 resultSet 객체가 존재하는 경우 닫힙니다.
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	} 
}
