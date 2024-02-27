package ex17_04_Runnable_Interface_anonymous_ramda;

public class RamdaExample3 {
	public static void main(String[] args) {
		print(new Sample() { // 생성된 것을 부모로 상속받아 사용.
			@Override
			public void print() {
				System.out.println("new Sample - 1");
			}
		});
		/* ---> */
		print(
			// new Sample() { // 생성된 것을 부모로 상속받아 사용.
			// @Override
			// public void print() {
				() -> System.out.println("new Sample - 1")// ;
			// }}
		);

		// 인터페이스 익명 객체를 생성하면서 메소드 부분 구현
		// 1. Sample 인터페이스 안의 비어있는 print 함수에 함수 자체를 넣는다. (람다식 3번 방법)
		print(() -> System.out.println("new Sample - 1"));
		print(() -> System.out.println("new Sample - 2"));
		print(() -> System.out.println("new Sample - 3"));
	}

	static void print(Sample sample) { // 2. 비어있던 함수를 받아 채우고 객체를 매개변수로 받아옴.
		sample.print(); // 3. 마지막으로 sample 의 print()를 실행한다.
	}
}
