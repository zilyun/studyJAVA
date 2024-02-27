package ex21_02_select_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3_Oracle2_where_input {

	public static void main(String[] args) {
		System.out.printf("검색어를 입력하세요>");
		try (Scanner scanner = new Scanner(System.in)) {
			String search = scanner.nextLine();

//		if (args.length != 1) {
//			System.out.println("상품명 입력하세요.");
//			return;
//		}

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "scott", "tiger");
				stmt = conn.createStatement();

				String select_sql = "select code, name, price, maker " + "from goodsinfo " + "where name = '" + search
						+ "'";
				// + "where name = \'" + args[0] + "\'";
				// + "where name = '디지털 TV'";

				rs = stmt.executeQuery(select_sql);

				System.out.println("번호 \t 상품코드 \t 상품명 \t\t 가격 \t 제조사 \t");
				System.out.printf("------------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");

				int i = 0;
				while (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					String maker = rs.getString("maker");

					// %s 쪽도 10으로 줘서 맞춰주도록 한다. - 왼쪽 정렬, 안 붙이면 오른쪽 정렬
					System.out.printf("%-5d\t %5d\t %-10s\t %5d\t %-10s\t \n", i++, code, name, price, maker);

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

}