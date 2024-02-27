package ex17_02_Thread_Sleep;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSleep_date implements Runnable {
	@Override
	public void run() {
		SimpleDateFormat s = new SimpleDateFormat("yyyy년MM월dd일 E요일 HH시mm분ss초");
		for(;;) {
			try {
				Date d = new Date();
				System.out.println(s.format(d));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}