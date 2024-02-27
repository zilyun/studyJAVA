package ex17_13_notifyAll_wait;
/* 자바에서 wait() 메서드는 스레드 동기화를 위한 Object 의 인스턴스 메서드이다.
 * Object 의 메서드이므로 어떤 객체에서도 호출이 가능하지만 오직 synchronized 블록 
 * 내에서만 호출이 가능하다. wait() 메서드의 기능이 객체에 대한 lock 을 release 
 * 하는 것이기 때문이며(좀 더 정확히는 제어권을 넘겨주는 것), 만약 wait()를 호출하는 
 * 스레드가 lock 을 소유하고 있지 않다면 에러가 발생된다.
 * 
 * 모니터 락 객체에 wait를 건 모든 스레드들에 대해 notify() 메서드를 사용하면 
 * 그 스레드들 중 하나를 임의로 깨우게 된다. 어느 스레드를 깨울지는 정해져있지 않으며 
 * 물론 구현에 따라 달라질 수도 있다.
 * notifyAll() 메서드는 이름에서 알 수 있듯이, wait를 건 모든 스레드들을 한 번에 
 * 깨우게 된다.
 * */
public class MultithreadExample {
	public static void main(String[] args) {
		SharedArea obj = new SharedArea();

		CalcThread t1 = new CalcThread(obj);
		PrintThread t1_1 = new PrintThread(obj);
		SimplePrintThread t1_2 = new SimplePrintThread(obj);
		LuxuryPrintThread t1_3 = new LuxuryPrintThread(obj);

		t1.start();
		t1_1.start();
		t1_2.start();
		t1_3.start();
	}
}
