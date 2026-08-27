
public class BankAccount {
	private int balance;
	
	public BankAccount(int initialBalance) {
		this.balance = initialBalance;
	}
	
	public void withdraw(int amount) throws InsufficientBalanceException{
		if(amount > balance) {
			throw new InsufficientBalanceException("残高不足です");
		}
		balance -= amount;
		System.out.println("引き出しました");
	}
}
