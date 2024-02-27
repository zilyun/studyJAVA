package ex17_08_syn_Solution;

public class SharedArea {
	private Account account1;
	private Account account2;
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
