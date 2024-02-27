package ex17_12_notify_wait;

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
		
		s.setResult(total * 4);
		s.setReady(true); // 파이 계산 끝났다라는 의미 
		
		synchronized (s) {
			s.notify(); // 다른 스레드로 신호를 보내는 메소드
		}
	}
}
