package assignmentThreads;

public class Main {
	
	private BankAccount localBalance;

	public Main(BankAccount localBalance) {
		this.setLocalBalance(localBalance);
	}

	public static void main(String[] args) {
		
		int numberOfCards = Integer.parseInt(args[0]);
		int localBalance = Integer.parseInt(args[1]);
		
		BankAccount account = new BankAccount(localBalance);
		
		for (int i = 1; i <= numberOfCards; i++) {
			Card j = new Card(i, account, localBalance);
			j.start();
			}
		
	  }

	
	public BankAccount getLocalBalance() {
		return localBalance;
	}

	public void setLocalBalance(BankAccount localBalance) {
		this.localBalance = localBalance;
	}

}