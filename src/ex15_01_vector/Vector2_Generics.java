package ex15_01_vector;
import java.util.*;

public class Vector2_Generics {

//	Vector 클래스 - ArrayList의 구버전 
//				 ArrayList와 거의 비슷, 둘 중 ArrayList를 더 많이 사용
	
	public static void main(String[] args) {
		Vector<String> vec = new Vector<String>(); // String 형만 올 수 있음.
		vec.add("Apple");
		vec.add("banana");
		vec.add("oRANGE");
		
		for(int i = 0; i < vec.size(); i++) {
			String temp = vec.get(i);
			System.out.println(temp);
		}
	}
}