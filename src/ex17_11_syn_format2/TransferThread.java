package ex17_11_syn_format2;

public class TransferThread extends Thread {
	private SharedArea s;

	TransferThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		for (int cnt = 0; cnt < 12; cnt++) {
				s.transfer();
				//System.out.print("회사 계좌: 100만원 인출,");
				//System.out.println("직원 계좌: 100만원 인출");
		}
	}
}
