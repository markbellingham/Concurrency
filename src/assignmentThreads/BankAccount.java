package assignmentThreads;

public class BankAccount {
	
	private static int localBalance;
	private static int transactionNumber;
	private static int threadNumber;
	private static int withdrawal;
	private static int deposit;
	private static int balance;
	
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
	
	
	public static void print() {
		for (int i = 0; i < Card.getTransaction().size(); i++) {
			transactionNumber = i + 1;
			threadNumber = Card.getTransaction().get(i)[0];
			withdrawal = Card.getTransaction().get(i)[1];
			deposit = Card.getTransaction().get(i)[2];
			balance = Card.getTransaction().get(i)[3];
			
			System.out.print("   " + transactionNumber);
			System.out.print(" (" + threadNumber + ")\t|\t   ");
			if (withdrawal > 0) {System.out.print(withdrawal + "\t\t|\t\t\t|\t   ");}
			if (deposit > 0) {System.out.print("\t\t|\t   " + deposit + "\t\t|\t   ");}
			System.out.print(balance);
			System.out.println();
			withdrawal = 0; deposit = 0;
		}
	}
	
}