package ex17_10_syn_format1;

public class SharedArea {
	private Account account1;
	private Account account2;
	
	void transfer() {
		synchronized (this) { // 호출되었을 때 자기 자신
			account1.withdraw(1000000);
			account2.deposit(1000000);
		}
	}
	
	int getTotal() {
		synchronized (this) { // 호출되었을 때 자기 자신
			return account1.getBalance() + account2.getBalance();
		}
	}
	
	public SharedArea(Account account1, Account account2) {
		this.account1 = account1;
		this.account2 = account2;
	}
	public Account getAccount1() {
		return account1;
	}
	public void setAccount1(Account account, Account account1) {
		this.account1 = account1;
	}
	public Account getAccount2() {
		return account2;
	}
	public void setAccount2(Account account2) {
		this.account2 = account2;
	}
}
