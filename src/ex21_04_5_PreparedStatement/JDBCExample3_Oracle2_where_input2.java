package ex21_04_5_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCExample3_Oracle2_where_input2 {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");

			String sql = "insert into goodsinfo " 
					   + "values(?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			// 이 문장을 주석으로 처리하면 에러 - 인덱스에서 누락된 IN 또는 OUT 매개변수 :: 1
			pstmt.setString(1, "A5000");
			pstmt.setString(2, "키보드");
			pstmt.setInt(3, 300);
			pstmt.setString(4, "손의나라");
			int rowNum = pstmt.executeUpdate();

			System.out.println(rowNum + "행이 추가되었습니다.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
