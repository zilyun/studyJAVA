package ex17_04_Runnable_Interface_anonymous_ramda;

// 1. 람다식은 메서드를 하나의 식으로 표현한 것입니다.
/* 
 * 2. 람다식은 단지 1개의 추상 메소드로 구성된 인터페이스 구현 객체만 람다식으로 표현할 수 있습니다.
 * 
 * 3. 이 인터페이스를 함수형 인터페이스(Functional Interface)라고 합니다.
 * 
 * 4. annotation 은 @FunctionalInterface를 붙입니다.
 * 	  * annotation 은 선택사항으로 이것을 붙이면 두 개 이상의 추상메소드가 선언되면 오류를 발생시킵니다.
 * 
 * 5. 람다식은 함수형 인터페이스를 다음과 같이 연산식 형태로 표현한 것입니다.
 * * 형식
 * 		(타입 매개변수) -> { 실행문; 실행문; .... }
 *  (1) (타입 매개변수) 			→ 람다식의 선언부
 *  	-> 						→ 람다식 연산자
 *  	{ 실행문; 실행문; .... }	→ 구현부 
 * 
 * 	(2) 매개변수 타입은 실행 도중 대입하는 값에 따라 자동으로 추론할 수 있기 때문에 타입을 생략해도 됩니다.
 * 	
 * 	(3) 매개변수가 하나 있다면 괄호를 생략할 수 있지만 매개변수가 없으면 괄호가 꼭 있어야 합니다.
 * 
 * 	(4) 실행문이 하나 있다면 중괄호를 생략할 수 있습니다.
 * 		단, 실행문이 하나의 return 문이라면 return 키워드도 생략해야 합니다. 
 * 
 * */
// 인터페이스 익명 구현 객체 
public class RamdaExample {

	public static void main(String[] args) {
		Negative n;

		// 인터페이스 익명 구현 객체
		n = new Negative() {
			@Override
			public int neg(int x) {
				return -x;
			}
		};
		System.out.println(n.neg(-10));

		// 모든 동잃한 람다식 표현
		// 방법1.
		n = (int x) -> {
			return -x;
		};
		System.out.println(n.neg(-20));

		// 방법2.
		// 매개변수 타입은 실행 도중 대입하는 값에 따라 자동으로 추론할 수 있기 떄문이다.
		n = (x) -> {
			return -x;
		};
		System.out.println(n.neg(-30));

		// 방법3.
		// 매개변수가 하나 있다면 괄호를 생략할 수 있지만 매개 변수가 없으면 괄호가 꼭 있어야 합니다.
		n = x -> {
			return -x;
		};
		System.out.println(n.neg(-40));

		// 방법4.
		// 실행문이 하나 있다면 중괄호 생략할 수 있습니다.
		// 단, 실행문이 하나의 return 문이라면 return 키워드도 생략해야 합니다.
		n = (int x) -> -x;
		System.out.println(n.neg(-50));

		// 방법5.
		n = (x) -> -x;
		System.out.println(n.neg(-60));

		// 방법6.
		n = x -> -x;
		System.out.println(n.neg(-70));
	}
}
