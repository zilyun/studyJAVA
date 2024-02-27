package ex21_04_6_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection connectionJDBC() {
		// 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		
		return conn;
	}
	
	public void rsClose(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
	}

	public void pstmtClose(PreparedStatement pstmt) {
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
	}
	
	public void connClose(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
	}
	
	public int delete (Student student) {
		int result = -1;
		
		try {
			conn = connectionJDBC();
			
			String delete_sql = "delete from student where no = ?";
			
			pstmt = conn.prepareStatement(delete_sql);
			pstmt.setInt(1, student.getNo());

			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			pstmtClose(pstmt);
			connClose(conn);
		}
		
		return result;
	}
	
	public int update (Student student) {
		int result = -1;
		
		try {
			conn = connectionJDBC();
			
			String update_sql = "UPDATE student SET NAME = ?, KOR = ?, "
							  + "MATH = ?, ENG = ?, TOT = ?, AVG = ?, " 
							  + "GRADE = (select GRADE from hakjum "
							  + "		  where ? between lowscore and hiscore) "
							  + "WHERE no = ?";
			
			pstmt = conn.prepareStatement(update_sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getKor());
			pstmt.setInt(3, student.getMath());
			pstmt.setInt(4, student.getEng());
			pstmt.setInt(5, student.getTot());
			pstmt.setFloat(6, student.getAvg());
			pstmt.setFloat(7, student.getAvg());
			pstmt.setInt(8, student.getNo());

			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			pstmtClose(pstmt);
			connClose(conn);
		}
		
		return result;
	}
	
	public Student select(int no) {
		Student student = null;
		
		try {
			conn = connectionJDBC();
			
			String select_sql = "select * from student where no = ? order by no";
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				student = new Student();
				student.setNo(rs.getInt("NO"));
				student.setName(rs.getString("NAME"));
				student.setKor(rs.getInt("KOR"));
				student.setMath(rs.getInt("MATH"));
				student.setEng(rs.getInt("ENG"));
				student.setTot(rs.getInt("TOT"));
				student.setAvg(rs.getFloat("AVG"));
				student.setGrade(rs.getString("GRADE"));
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}

		return student;
	}

	public int insert(Student student) {
		int result = 0;
		
		String student_insert_sql = "insert into "
								  + "student(NO, NAME, KOR, MATH, ENG, TOT, AVG, GRADE) "
								  + "values(student_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, "
							      + "	   (select GRADE from hakjum "
							      + "		where ? between lowscore and hiscore))";
		try {
			conn = connectionJDBC();

			pstmt = conn.prepareStatement(student_insert_sql);

			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getKor());
			pstmt.setInt(3, student.getMath());
			pstmt.setInt(4, student.getEng());
			pstmt.setInt(5, student.getTot());
			pstmt.setFloat(6, student.getAvg());
			pstmt.setFloat(7, student.getAvg());

			result = pstmt.executeUpdate();

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			pstmtClose(pstmt);
			connClose(conn);
		}

		return result;
	}

	public ArrayList<Student> selectAll() {
		ArrayList<Student> list = new ArrayList<Student>();

		try {
			conn = connectionJDBC();
			
			String select_sql = "select * from student order by no";
			pstmt = conn.prepareStatement(select_sql);

			rs = pstmt.executeQuery(select_sql);

			while (rs.next()) {
				Student s = new Student();
				s.setNo(rs.getInt("NO"));
				s.setName(rs.getString("NAME"));
				s.setKor(rs.getInt("KOR"));
				s.setMath(rs.getInt("MATH"));
				s.setEng(rs.getInt("ENG"));
				s.setTot(rs.getInt("TOT"));
				s.setAvg(rs.getFloat("AVG"));
				s.setGrade(rs.getString("GRADE"));
				list.add(s);
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			// 순서대로 닫을 것
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}
		return list;
	}

}
