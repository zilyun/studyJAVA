package ex21_03_insert_delete_update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample4_Oracle_insert_input1 {

	public static void main(String[] args) {
		// "A2000" "모니터" 200 "삼성"
		if (args.length != 4) {
			System.out.println("상품코드 상품명 가격 제조사를 입력하세요");
			return;
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();

			String sql = "insert into goodsinfo " + 
			" values('" + args[0] + 
					"', '" + args[1] + 
					"', " + args[2] + 
					", '" +  args[3] + "')";

			System.out.println(sql+"\n");

			int rowNum = stmt.executeUpdate(sql);
			System.out.println(rowNum + "행이 추가되었습니다.");
			
			String select_sql = "select * from goodsinfo";

			rs = stmt.executeQuery(select_sql);
			
			int i = 0;
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String maker = rs.getString("maker");
				
				// %s 쪽도 10으로 줘서 맞춰주도록 한다. - 왼쪽 정렬, 안 붙이면 오른쪽 정렬
				System.out.printf("%5s\t %5s\t %-10s\t %5d\t %-10s\t \n"
						, i++, code, name, price, maker);

			}

		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
	}

}
