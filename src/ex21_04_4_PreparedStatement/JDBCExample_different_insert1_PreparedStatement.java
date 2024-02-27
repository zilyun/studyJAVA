package ex21_04_4_PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// PreparedStatement
/*
 * 1. PreparedStatement의 부모는 Statement 입니다.
 * 2. SQL 문장은 실행을 위해서는 컴파일이 필요한데 PreparedStatement는 사전에 컴파일을 합니다.
 * 3. PreparedStaement는 SQL 문장이 미리 컴파일이 되기 때문에 조건문이 바뀌지 않고
 * 		조건값만 바뀌는 경우, 삽입할 데이터만 바뀌는 경우 등에 사용하면 좋습니다.
 * 		즉, 한번 미리 컴파일을 해두면 같은 쿼리에 대해서 컴파일은 하지 않고 값만 설정해서 수행합니다.
 * 		Statement 는 같은 쿼리라고 해도 매번 컴파일 과정을 거칩니다.
 * 		따라서 같은 쿼리를 실행하더라도 PreparedStatement가 Statement 보다 실행속도가 빠릅니다.
 * 			예) insert into test(no, ename) values(1, '홍길동');
 * 			예) insert into test(no, ename) values(1, '홍길동');
 * 			예) insert into test(no, ename) values(1, '홍길동');
 * 4. 3번을 적용하기 위해서 쿼리는 다음과 같이 변경합니다.
 * 	  바뀌는 값에 대해서 ?(위치홀더-placeholder-쿼리를 실행할 때 동적으로 값을 전달하는 매개변수)로 표시합니다.
 * 	  예)insert into test(no, ename) values(?, ?);
 * 
 * 5. ?에 대한 값을 설정하기 위해 set 자료형()메서드를 사용합니다.
 * 	  예) 첫번째 ?의 조건값이 정수형 1인 경우 setInt(1, 1)
 * 	  예) 두번째 ?의 조건값이 문자형 '홍길동'인 경우 setString(2, "홍길동")
 * 		Statement 는 SQL문장에서 single quotation(')이 필요한 경우 직접 작성해 주었지만 
 * 		PreparedStatement 는 자료형에 따라 알아서 single quotation(')를 처리해 줍니다.
 * */

public class JDBCExample_different_insert1_PreparedStatement {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			
			int result = 0, count = 0;
			long start = System.currentTimeMillis();
			stmt = conn.createStatement();
			
			for (int i = 0; i < 10000; i++) {
				String sql = "insert into test(no, name) "
						   + "values(" + i + ", '홍길동')";
				result = stmt.executeUpdate(sql);
				count += result;
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println("소요시간: "+ (end -start) + "ms");
			
			if (count == 10000)
				System.out.println("10000행이 추가되었습니다. stmt");
				
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
