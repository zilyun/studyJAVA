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

public class DAO3 {

	public List<Map<String, Object>> select() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();

			String select_sql = "select deptno, "
					+ "max(sal) sal, "
					+ "count(*) cnt "
					+ "from emp "
					+ "group by deptno "
					+ "order by deptno ASC";

			rs = stmt.executeQuery(select_sql);

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				String deptno = rs.getString("deptno");
				map.put("deptno", deptno);

				String sal = rs.getString("sal");
				map.put("sal", sal);

				int cnt = rs.getInt("cnt");
				map.put("cnt", cnt);

				list.add(map);
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
