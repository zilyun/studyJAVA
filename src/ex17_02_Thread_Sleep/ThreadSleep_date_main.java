package ex17_02_Thread_Sleep;

public class ThreadSleep_date_main {

	public static void main(String[] args) {
		Thread t = new Thread(new ThreadSleep_date());
		t.start();
	}

}
