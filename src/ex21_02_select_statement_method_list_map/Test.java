package ex21_02_select_statement_method_list_map;

import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		DAO dao = new DAO();
		List<GoodsInfo> list = dao.select();

		System.out.println("번호\t상품코드\t상품명\t\t가격\t제조사");
		System.out.println("-----------------------------------------------");

		for (GoodsInfo g : list) {
			System.out.println(g.toString());
		}
	}
}
