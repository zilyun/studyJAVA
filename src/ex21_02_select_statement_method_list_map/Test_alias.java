package ex21_02_select_statement_method_list_map;

import java.util.List;
import java.util.Map;

public class Test_alias {
	public static void main(String[] args) {
		DAO3 dao3 = new DAO3();
		List<Map<String, Object>> list = dao3.select();

		if (list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			System.out.println("번호\t부서번호\t최고급여\t부서인원수");
			System.out.println("-----------------------------------------------");
			int no = 1;
			for (Map<String, Object> h : list) {
				System.out.println(no++ + "\t" + h.get("deptno") + "\t" 
						+ h.get("sal") + "\t" + h.get("cnt") + "명");
			}
		}
	}
}
