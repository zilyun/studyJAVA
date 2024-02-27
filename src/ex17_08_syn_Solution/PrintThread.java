package ex17_08_syn_Solution;

public class PrintThread extends Thread {
	private SharedArea s;

	public PrintThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			// 동기화 시작 
			synchronized (s) {
				int remain = s.getAccount1().getBalance() + s.getAccount2().getBalance();
				System.out.println("계좌 잔액 합계: " + remain);
			}
			// 동기화 끝
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
}
