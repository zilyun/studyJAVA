package ex17_04_Runnable_Interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class RunnableThread_2024_01_30 implements Runnable {
	public void run() {
		while (true) {
			// 현재 날짜/시간
			LocalDateTime now = LocalDateTime.now();
			// 포맷팅 (년/월/일)
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 "));
			// 포맷팅 (요일)
			String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN).toString();
			// 포맷팅 (시/분/초)
			String formatedNow2 = now.format(DateTimeFormatter.ofPattern(" HH시mm분ss초"));

			try {
				// 포맷팅 현재 날짜/시간 출력
				System.out.println(formatedNow+dayOfWeek+formatedNow2); // 2021년 06월 17일 06시 43분 21초
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
