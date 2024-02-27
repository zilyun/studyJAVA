package ex17_08_syn_problem;
/* 39... <- JVM이 계산 중에 읽어와서 생긴 현상
 * 40...
 * 40...
 * 
 * 40... <- JVM이 계산을 끝내고 읽어와서 생긴 현상
 * 40...
 * 40... 
 * - 문제 해결법
 *   동기화: 공유 데이터 사용 중에 그 공유 데이터를 다른 스레드가 사용하지 못하도록 만드는 것을 의미합니다.
 *   동기화란 인터넷에 저장되어 있는 자신의 데이터 자료와 자신의 스마트폰 또는 컴퓨터의 자료를 서로 주고받아서 정보의 최신성을 동일하도록 만드는 것을 의미합니다.
 */
public class MultithreadExample {
	public static void main(String[] args) {
		Account a1 = new Account("111-111-1111", "회사", 20000000);
		Account a2 = new Account("222-222-2222", "직원", 10000000);
		SharedArea s= new SharedArea(a1, a2);
		TransferThread t1 = new TransferThread(s);
		PrintThread t2 = new PrintThread(s);
		t1.start();
		t2.start();
	}
}
