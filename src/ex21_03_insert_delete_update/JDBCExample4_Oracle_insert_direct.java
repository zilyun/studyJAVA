package ex21_03_insert_delete_update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample4_Oracle_insert_direct {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();

			/*
			 * 1. 직접 데이터를 입력하는 경우 insert into goodsinfo values('A1000', '고급 핸드폰', 100, 'LG')
			 * 
			 */

			String sql = "insert into goodsinfo " + " values('A1000', '고급 핸드폰', 100, 'LG')";

			System.out.println(sql);

			/*
			 * INSERT, UPDATE, DELETE 문을 실행하는 메서드는 executeUpdate()입니다. executeUpdate()의 리턴값은
			 * INSERT, UPDATE, DELETE 수행 된 row의 수입니다.
			 */

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
