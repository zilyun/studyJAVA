package ex16_01_Map;
import java.util.*;
// 내부적으로 HashMap을 이용해서 만들어졌으며 HashSEt이란 이름은 해싱을 이용해서 구현했기 때문에 붙여진 것입니다.
public class HashSet2_Generics_add_print {

	public static void main(String[] args) {
		// HashSet 객체 생성
		HashSet<String> set = new HashSet<String>();
		// 데이터 생성 
		set.add("짜바");
		set.add("카푸치노");
		set.add("에스프레소");
		System.out.println("저장된 데이터의 수 = "+ set.size());
		System.out.println(set);
		
		// iterator(): set에 있는 데이터를 모두 가져옵니다.
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
		}
	}
}
