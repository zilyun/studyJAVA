package ex17_07_ShareArea_Solution;
// CalcThread가 계산된 파이 값을 공유 영역에 쓰기도 전에
// PrintThread가 공유 영역의 데이터를 읽어 갔기 때문에
// result 필드가 가지고 있던 기본값 0.0이 출력된 것입니다.
// 해결책? Thread.sleep(10000); -> isReady 해결법
public class Main {
	public static void main(String[] args) {
		SharedArea obj = new SharedArea();

		CalcThread t1 = new CalcThread(obj);
		PrintThread t2 = new PrintThread(obj);
		
		t1.start();
		t2.start();
	}
}
