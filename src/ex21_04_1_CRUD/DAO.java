package ex21_04_1_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {

	public ArrayList<Emp> select(String menu, String searchData) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Emp> list = new ArrayList<Emp>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();
			String single = "'";
			String whereCondition = "";

			switch (menu) {
				case "사원번호" -> whereCondition += "where empno = " + searchData;
				case "사원이름" -> whereCondition += "where ename = " + single + searchData + single;
				case "직급" -> whereCondition += "where job = " + single + searchData + single;
				case "매니저" -> whereCondition += "where mgr = " + single + searchData + single;
				case "입사일자" -> whereCondition += "where hiredate = " + single + searchData + single;
				case "급여" -> whereCondition += "where sal = " + searchData;
				case "커미션" -> whereCondition += "where comm = " + searchData;
				case "부서번호" -> whereCondition += "where deptno = " + searchData;
				default -> whereCondition += "";
			}

			String select_sql = "select * from emp " + whereCondition;

			rs = stmt.executeQuery(select_sql);

			while (rs.next()) {
				Emp e = new Emp();
				e.setEmpno(rs.getInt("empno"));
				e.setEname(rs.getString("ename"));
				e.setJob(rs.getString("job"));
				e.setMgr(rs.getInt("mgr"));
				e.setHiredate(rs.getDate("hiredate"));
				e.setSal(rs.getInt("sal"));
				e.setComm(rs.getInt("comm"));
				e.setDeptno(rs.getInt("deptno"));
				list.add(e);
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

	public ArrayList<Emp> selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Emp> list = new ArrayList<Emp>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();

			String select_sql = "select * from emp";

			rs = stmt.executeQuery(select_sql);

			while (rs.next()) {
				Emp e = new Emp();
				e.setEmpno(rs.getInt("empno"));
				e.setEname(rs.getString("ename"));
				e.setJob(rs.getString("job"));
				e.setMgr(rs.getInt("mgr"));
				e.setHiredate(rs.getDate("hiredate"));
				e.setSal(rs.getInt("sal"));
				e.setComm(rs.getInt("comm"));
				e.setDeptno(rs.getInt("deptno"));
				list.add(e);
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
