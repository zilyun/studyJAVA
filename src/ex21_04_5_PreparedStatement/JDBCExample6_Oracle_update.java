package ex21_04_5_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCExample6_Oracle_update {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("상품코드,제조사 입력하세요");
			return;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");

			String sql = "update goodsinfo "
					   + "set  maker = ? "
					   + "where code = ? ";

			pstmt = conn.prepareStatement(sql);

			// 이 문장을 주석으로 처리하면 에러 - 인덱스에서 누락된 IN 또는 OUT 매개변수 :: 1
			pstmt.setString(1, args[1]); // "HTA"
			pstmt.setString(2, args[0]); // "10001"
			int rowNum = pstmt.executeUpdate();

			System.out.println(rowNum + "행이 수정 되었습니다.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
