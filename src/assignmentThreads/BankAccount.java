package assignmentThreads;

public class BankAccount {
	
	private int localBalance;
	
	public void call(int cardId, BankAccount account) {
		System.out.println("Card ID: " + cardId + ", (Thread Id: " + Thread.currentThread().getId() + ")");
		System.out.println("Transaction Type: " + Card.type);
		System.out.println("Transaction Amount: " + Card.transactionAmount);
		System.out.println("Account Balance: " + localBalance);
		System.out.println();
	}
	
	public BankAccount(int startingBalance) {
		localBalance = startingBalance;
	}
	
	public int withdraw(int transactionAmount) {
		while (transactionAmount > localBalance) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		localBalance -= transactionAmount;
		return localBalance;
	}
	
	public int deposit(int transactionAmount) {
		localBalance += transactionAmount;
		notifyAll();
		return localBalance;
	}
}