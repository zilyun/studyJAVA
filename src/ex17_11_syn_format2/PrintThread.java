package ex17_11_syn_format2;

public class PrintThread extends Thread {
	private SharedArea s;

	public PrintThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
				System.out.println("계좌 잔액 합계: " + s.getTotal());
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
}
