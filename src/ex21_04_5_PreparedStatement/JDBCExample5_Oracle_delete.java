package ex21_04_5_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCExample5_Oracle_delete {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("상품코드 입력하세요");
			return;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");

			String sql = "delete from goodsinfo " 
					   + "where code = ?";

			pstmt = conn.prepareStatement(sql);

			// 이 문장을 주석으로 처리하면 에러 - 인덱스에서 누락된 IN 또는 OUT 매개변수 :: 1
			pstmt.setString(1, args[0]);
			int rowNum = pstmt.executeUpdate();

			System.out.println(rowNum + "행이 삭제 되었습니다.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
