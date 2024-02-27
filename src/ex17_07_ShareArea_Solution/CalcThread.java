package ex17_07_ShareArea_Solution;

public class CalcThread extends Thread {
	private SharedArea s;

	public CalcThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		double total = 0.0;

		for (int cnt = 1; cnt < 1000000000; cnt += 2) {
			if (cnt / 2 % 2 == 0)
				total += 1.0 / cnt;
			else
				total -= 1.0 / cnt;
		}
		// 계산결과를 공유 영역에 저장
		s.setResult(total * 4);
		// SharedArea 객체의 isReady 필드 값을 true로 설정
		s.setReady(true);
	}
}
