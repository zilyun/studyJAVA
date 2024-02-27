package ex17_13_notifyAll_wait;

public class LuxuryPrintThread extends Thread {

	private SharedArea s;

	public LuxuryPrintThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		if (s.isReady() != true) { // 파이가 계산 중인 경우
			try {
				synchronized (s) { 
					s.wait(); // 다른 스레드로부터 신호를 기다립니다.
							  // 파이의 계산이 끝나면 제어권을 넘겨줌.
				}
			} catch (InterruptedException e) { // wait 메소드가 발생하는 익셉션 처리 
				System.out.println(e.getMessage());
			}
		}
		System.out.println("threadπ: "+s.getResult());
	}
}
