package ex17_07_ShareArea_Solution;

public class PrintThread extends Thread {

	private SharedArea s;

	public PrintThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (s.isReady() != true) {
			System.out.println("실행 중 ~");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("공유 영역의 데이터 = " + s.getResult());
	}
}
