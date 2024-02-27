package ex21_02_select_statement_method_list_map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO {

	public List<GoodsInfo> select() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<GoodsInfo> list = new ArrayList<GoodsInfo>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();

			String select_sql = "select * from goodsinfo";

			rs = stmt.executeQuery(select_sql);

			while (rs.next()) {
				GoodsInfo g = new GoodsInfo();
				String code = rs.getString("code");
				g.setCode(code);

				String name = rs.getString("name");
				g.setName(name);

				int price = rs.getInt("price");
				g.setPrice(price);

				String maker = rs.getString("maker");
				g.setMaker(maker);

				list.add(g);
			}

		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException se) {
					System.out.println(se.getMessage());
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException se) {
					System.out.println(se.getMessage());
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		}
		return list;
	}
}
