package assignmentThreads;

public class BankAccount {
	
	private int 		localBalance;
	private static int 	transactionNumber;
	private static int 	threadNumber;
	private static int 	withdrawalAmount;
	private static int 	depositAmount;
	private static int 	balance;
	
	// Associates the starting balance with the account
	public BankAccount(int startingBalance) {
		localBalance = startingBalance;
	}
	
	// Method for withdrawing from account
	// If the card owner tries to take more than is available, they have to wait until funds are available
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
	
	// Method for depositing into account
	// When completed it notifies all threads so that threads waiting can check again if they are able to withdraw
	public int deposit(int transactionAmount) {
		localBalance += transactionAmount;
		notifyAll();
		return localBalance;
	}
	
	
	// Method for printing the transactions from the ArrayList
	public static void print() {
		for (int i = 0; i < Card.getTransaction().size(); i++) {
			// Give all the transaction data elements easy to understand names
			transactionNumber 	= i + 1;
			threadNumber 		= Card.getTransaction().get(i)[0];
			withdrawalAmount 	= Card.getTransaction().get(i)[1];
			depositAmount 		= Card.getTransaction().get(i)[2];
			balance 			= Card.getTransaction().get(i)[3];
			
			// Print each transaction formatted with tabs and spaces
			System.out.print("   " + transactionNumber);
			System.out.print(" (" + threadNumber + ")\t|\t   ");
			if (withdrawalAmount > 0) {System.out.print(withdrawalAmount + "\t\t|\t\t\t|\t   ");}
			if (depositAmount > 0) {System.out.print("\t\t|\t   " + depositAmount + "\t\t|\t   ");}
			System.out.print(balance);
			System.out.println();
			// Reset these variables so that they don't carry through to the next transaction.
			withdrawalAmount = 0; depositAmount = 0;
		}
	}
	
}