package ex15_01_ArrayList;

import java.util.ArrayList;
// 제너릭
// 반드시 그 타입이 들어와야 한다는 것을 명시적으로 표현한 것이다.
// 와일드카드
// 제너릭 타입을 매개값이나 리턴 타입으로 사용할 때 구체적인 타입 대신 와일드 카드를 사용할 수 있다.
// 1. <?> => 타입 파라미터를 대치하는구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.
// 2. <? extends 상위타입> => 타입 파라미터를 대치하는 구체적인 타입으로 특정 클래스를 
//							상속받은 클래스로 제한한다.
// 3. <? super 하위타입> => 타입 파라미터를 대치하는 구체적인 타입으로 특정 클래스와 
//							그 클래스의 상위 클래스로만 제한한다.
public class WildTest {
	public static void main(String[] args) {
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		list2.add(Integer.valueOf(3));
		
		ArrayList<Double> list3 = new ArrayList<Double>();
		list3.add(10.1);
		list3.add(11.2);
		list3.add(12.3);
		
		// static 메소드 호출
		printData(list2);
		printData(list3);
	}
	
	// static public void printData(ArrayList<Object> list) // 오류 발생 -> <>만은 같은 자료형이어야 한다.
	static public void printData(ArrayList<? extends Number> list) { 
		// java.lang.Object -> java.lang.Number -> java.lang.Double, java.lang.Integer
		// 상위의 패키지로 표현이 가능하다.
		for(Object i: list) {
			System.out.println(i);
		}
	}
}
