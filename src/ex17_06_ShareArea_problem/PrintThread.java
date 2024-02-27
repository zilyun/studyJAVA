package ex17_06_ShareArea_problem;

public class PrintThread extends Thread {

	private SharedArea s;

	public PrintThread(SharedArea s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			// Thread.sleep(10000);
			System.out.println("공유 영역의 데이터 = "+s.getResult());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
