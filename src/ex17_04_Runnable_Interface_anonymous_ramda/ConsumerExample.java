package ex17_04_Runnable_Interface_anonymous_ramda;

import java.util.ArrayList;
import java.util.function.Consumer;

/*
 * java.util.function 패키지에 일반적으로 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 미리 정의해 놓았습니다.
 * Consumer<T> 는 한 개의 매개변수가 있고 반환값이 없는 함수형 인터페이스
 *  @FunctionalInterface
 *	public interface Consumer<T> {
 *  	void accept(T t);
 *  }
 * 
 *  default Consumer<T> andThen(Consumer<? super T> after) {
 *   	Objects.requireNonNull(after);
 *   	return (T t) -> { accept(t); after.accept(t); };
 * 	}
 * 인터페이스 이름이 소비자(Consumer)인 이유는 데이터(매개변수)를 소비(사용)하고 아무것도 생성(반환)하지 않기 때문입니다.
 * 인터페이스 내부에는 추상 메서드 accept()와 디폴트 메서드인 andThen() 메서드가 존재합니다.
 * accept() 메서드는 제네릭 타입인 한 개의 매개변수를 전달받아 특정 작업을 수행합니다.
 */
public class ConsumerExample {

	public static void main(String[] args) {
		Consumer<String> a = i -> System.out.println(i);
		a.accept("a:람다식");

		Consumer<String> b = System.out::println;
		b.accept("b: 메서드 참조 방법");

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		// void java.util.ArrayList.forEach(Consumer<? super Integer> action)
		/*
		 * for (T t : this) action.accept(t); --> Consumer 인터페이스 확인(상속 받아서 함수를 만들어 사용)
		 * 
		 * @Override 
		 * public void forEach(Consumer<? super E> action) { 
		 * 		// Consumer 의 action (매개함수), accept (실행함수)
		 *		// ArrayList의 T t : this (매개변수)  
		 * 		...
		 * 		for (T t : this)
         *			action.accept(t); // 실행함수 안의 매개변수 t를 입력하여 매개함수를 실행 
         *		... 
		 * 	}
		 * 
		 *  Consumer<? super Integer>은 Integer 타입 또는
		 *  Integer 의 상위 클래스를 받아들일 수 있는 Consumer 를 나타냅니다.
		 */
		Consumer<Integer> c = i -> System.out.println(i);
		list.forEach(i -> c.accept(i));
//		list.forEach(i -> System.out.println(i)); // i는 배열의 값들
//		list.forEach(System.out::println);
		
		// 알고리즘 문제 풀 때 사용법으로 정리
//		int[] answer = new int[6]; 
//		list.forEach(i -> answer[i] = i); // 람다식
//		for(int ans : answer) {
//			System.out.println(ans);
//		}
	}
}
