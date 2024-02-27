package ex17_04_Runnable_Interface_anonymous_ramda;

// 예제에서의 annotation 은 @FunctionalInterface를 붙입니다.
@FunctionalInterface // 3. 이렇게 쓰도록 내부적으로 선언된 것입니다.
public interface Negative {
	int neg(int x);
	// int neg2(int x); 
	// 1. @FunctionalInterface 어노테이션은 선택사항으로 이것을 붙이면 두 개 이상의 추상메소드가 선언되면 오류를 발생시킵니다. 
	// 2. EX) 위에 주석 제거해보기
}
/* 어노테이션은 사전적 의미로는 주석이라는 뜻이다. 자바에서 사용될 때의 어노테이션은 코드 사이에 주석처럼 쓰여서 특별한 의미, 
   기능을 수행하도록 하는 기술이다. 즉, 프로그램에게 추가적인 정보를 제공해주는 메타데이터(meta data: 데이터를 위한 데이터)라고 볼 수 있다.
   
   다음은 어노테이션의 용도를 나타낸 것이다.
   - 컴파일러에게 코드 작성 문법 에러를 체크하도록 정보를 제공
   - 소프트웨어 개발툴이 빌드나 배치시 코드를 자동으로 생성할 수 있도록 정보 제공
   - 실행시(런타임시)특정 기능을 실행하도록 정보를 제공
*/