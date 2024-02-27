package ex21_02_select_statement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample3_Oracle1_emp {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1단계 : JDBC 드라이버를 로드한다.
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);

			// 2단계 : DB에 연결한다.
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");

			/*
			 * 데이터 베이스에 있는 테이블의 데이터를 읽어오기 위해 Statement 객체가 필요합니다. 2단계에서 얻은 Connection 객체에 대해
			 * createStatement 메소드를 호출해서 얻습니다.
			 */
			stmt = conn.createStatement();

			/*
			 * Ststement 타입은 java.sql 패키지에 속하는 인터페이스 이름으로 select 문으로 실행하는 executeQuery 라는
			 * 메소드가 있습니다. 이 메소드에 파라미터로 select 문장을 넘겨주어야 합니다. 이 메소드는 파라미터로 넘겨준 select 문을 데이터
			 * 베이스로 보내서 실행하고 그 결과로 ResultSet 객체를 리턴합니다.
			 */
			String select_sql = "select * from emp";

			rs = stmt.executeQuery(select_sql);

			System.out.println("번호 \t 사원번호 \t 사원이름 \t 직급\t\t  상사\t 입사일\t\t 급여\t 커미션\t 부서번호\t");
			System.out.printf("------------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");

			/*
			 * ResultSet 객체로부터 select 문의 실행 결과를 얻기 위해서는 먼저 next() 메소드를 호출해야 합니다.
			 */

			int i = 0;
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				// Date hiredate = rs.getDate("hiredate");
				String hiredate = rs.getString("hiredate").substring(0, 10);
				String sal = rs.getString("sal");
				String comm = rs.getString("comm");
				int deptno = rs.getInt("deptno");
				
				// %s 쪽도 10으로 줘서 맞춰주도록 한다. - 왼쪽 정렬, 안 붙이면 오른쪽 정렬
				System.out.printf("%5d\t %5d\t %s\t %-10s\t %5d\t %s\t %s\t %s\t %5d\n"
						, i++, empno, ename, job, mgr, hiredate, sal, comm, deptno);
			
				
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
	}

}
