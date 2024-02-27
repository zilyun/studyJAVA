package ex21_04_4_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCExample_different_insert3_PreparedStatement {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("상품명 입력하세요.");
			return;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");

			String sel_where_sql = "select code, name, price, maker " + "from goodsinfo " + "where name = ?";

			pstmt = conn.prepareStatement(sel_where_sql);

			// 이 문장을 주석으로 처리하면 에러 - 인덱스에서 누락된 IN 또는 OUT 매개변수 :: 1
			pstmt.setString(1, "전자사전");
			rs = pstmt.executeQuery();

			System.out.println("번호\t상품코드\t상풍명\t\t가격\t제조사");
			System.out.println("-------------------------------------------");

			int i = 0;
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String maker = rs.getString("maker");
				System.out.printf("%d\t%-7s\t%-10s\t%d\t%-5s\n", ++i, code, name, price, maker);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
