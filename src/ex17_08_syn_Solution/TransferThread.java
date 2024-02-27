package ex17_08_syn_Solution;

public class TransferThread extends Thread {
	private SharedArea s;

	TransferThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		for (int cnt = 0; cnt < 12; cnt++) {
			// 동기화 시작
			synchronized (s) {
				s.getAccount1().withdraw(1000000);
				System.out.print("회사 계좌: 100만원 인출,");
				s.getAccount2().deposit(1000000);
				System.out.println("직원 계좌: 100만원 인출");
			}
			// 동기화 끝
		}
	}
}
