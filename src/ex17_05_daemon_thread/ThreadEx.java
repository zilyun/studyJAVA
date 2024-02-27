/* 스레드는 일반 스레드와 데몬 스레드로 나뉩니다.
 * 데몬 스레드는 다른 일반 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드입니다.
 * 일반 스레드가 모두 종료되면 데몬 스레드는 강제적으로 자동 종료됩니다.
 * 데몬 스레드는 일반 스레드의 보조 역할을 수행하므로 일반 스레드가 모두 종료되고 나면 
 * 데몬 스레드의 존재의 의미가 없기 때문입니다.
 * 이 점을 제외하고는 데몬 스레드와 일반 스레드는 다르지 않습니다.
 * 데몬 스레드의 예는 가비지 컬렉터, 워드프로세서의 자동저장, 미디어 플레이어의 동영상 및 음악재생 등이 있습니다.
 * 이 기능들은 주 스레드 JVM, 워드프로세서, 미디어 플레이어가 종료되면 같이 종료됩니다.
 * 
 * 데몬 스레드는 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 
 * 작업을 수행하고 다시 대기하도록 작성합니다.
 * */

package ex17_05_daemon_thread;

public class ThreadEx {
	public static void main(String[] args) {
		Thread t = new Thread(new Threadautosave());
		t.setDaemon(true);
		t.start();
		
		for(int i = 1; i <= 30; i++) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
			
			if(i == 5)
				Threadautosave.autoSave = true;
		}	
	}
}
