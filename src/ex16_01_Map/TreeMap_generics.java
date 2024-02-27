package ex16_01_Map;
import java.util.*;

// 이진 검색 트리라는 자료구조의 형태로 데이터를 저장하는 컬렉션 클래스입니다. 정렬이 됩니다.
public class TreeMap_generics {

	public static void main(String[] args) {
			TreeMap<String, String> tm = new TreeMap<String, String>();
			
			tm.put("woman", "재미있니");
			tm.put("man", "좋은하루");
			tm.put("age", new String("10"));
			tm.put("city", "seoul");
			tm.put("city", "Busan");
			
			System.out.println(tm);

	}

}
