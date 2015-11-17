package assignmentThreads;

public class BankAccount {
	
	private int localBalance;
	
	//Associates the starting balance with the account
	public BankAccount(int startingBalance) {
		localBalance = startingBalance;
	}
	
	//Method for withdrawing from account
	//If the card owner tries to take more than is available, they have to wait until funds are available
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
	
	//Method for depositing into account
	//When completed it notifies all threads so that threads waiting can check again if they are able to withdraw
	public int deposit(int transactionAmount) {
		localBalance += transactionAmount;
		notifyAll();
		return localBalance;
	}
	
	
	//Prints the results of each transaction showing that they occur as atomic units
	public void print(int transactionNumber, BankAccount account, String type, int transactionAmount) {
		//Print out the transaction number (formatted to 2 digits) and thread number
		System.out.print("   " + String.format("%02d", transactionNumber) + "(" + Thread.currentThread().getId() + ")");
		//Switch decides position of the transaction depending on whether it's a withdrawal or deposit
		switch(type) {
		case "Withdrawal":
			System.out.print("\t\t   " + transactionAmount + "\t\t\t\t\t\t   ");
			break;
		case "Deposit":
			System.out.print("\t\t\t\t\t   " + transactionAmount + "\t\t\t   ");
			break;
		}			
		//Prints out the running total
		System.out.print(localBalance);
		System.out.println();
		}
	
	
	/*
	public void call(int cardId, BankAccount account, String type, int transactionAmount) {
		System.out.println("Card ID: " + cardId + ", (Thread Id: " + Thread.currentThread().getId() + ")");
		System.out.println("Transaction Type: " + type);
		System.out.println("Transaction Amount: " + transactionAmount);
		System.out.println("Account Balance: " + localBalance);
		System.out.println();
	}
	*/
	
}