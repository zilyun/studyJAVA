package ex17_04_Runnable_Interface_anonymous_ramda;

public class RamdaExample2 {
	public static void main(String[] args) {
		Parse p;
		
		// 인터페이스 익명 구현 객체
		p = new Parse() {
			@Override
			public int toInt(String x) {
				return Integer.parseInt(x);
			}
		};
		System.out.println(p.toInt("100"));
		
		// 모든 동잃한 람다식 표현
		// 방법1.
		p = (String x) -> {
			return Integer.parseInt(x);
		};
		System.out.println(p.toInt("200"));

		// 방법2.
		// 매개변수 타입은 실행 도중 대입하는 값에 따라 자동으로 추론할 수 있기 떄문이다.
		p = (x) -> {
			return Integer.parseInt(x);
		};
		System.out.println(p.toInt("300"));

		// 방법3.
		// 매개변수가 하나 있다면 괄호를 생략할 수 있지만 매개 변수가 없으면 괄호가 꼭 있어야 합니다.
		p = x -> {
			return Integer.parseInt(x);
		};
		System.out.println(p.toInt("400"));

		// 방법4.
		// 실행문이 하나 있다면 중괄호 생략할 수 있습니다.
		// 단, 실행문이 하나의 return 문이라면 return 키워드도 생략해야 합니다.
		p = (String x) -> Integer.parseInt(x);
		System.out.println(p.toInt("500"));

		// 방법5.
		/* 방법 4는 값 하나를받아서 Integer.parseInt()에 넘겨주는 일을 하고 있습니다.
		 * 즉 하나의 메서드를 호출하고 있습니다.
		 * 이것을 메서드 참조 방법으로 나타낼 수 있습니다.
		 * 메서드 참조는 클래스이름::메서드이름 또는 참조변수::메서드이름 형식으로 작성합니다.
		 * */
		p = Integer::parseInt;
		System.out.println(p.toInt("600"));
	}

}
