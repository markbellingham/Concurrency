package assignmentThreads;

public class Main {
	
	private BankAccount localBalance;

	public Main(BankAccount localBalance) {
		this.setLocalBalance(localBalance);
	}

	public static void main(String[] args) {
		
		//Program is run with two arguments
		//args[0] is the number of cards that have access, args[1] is the opening balance
		int numberOfCards = Integer.parseInt(args[0]);
		int localBalance = Integer.parseInt(args[1]);
		
		//Instantiates the account
		BankAccount account = new BankAccount(localBalance);
		
		//Initialises each card and starts the associated thread
		for (int i = 1; i <= numberOfCards; i++) {
			Card j = new Card(i, account, localBalance);
			j.start(); //Starts the run() method in the Card class
			}
		
	  }
	
	//Getters and Setters for local balance
	public BankAccount getLocalBalance() {
		return localBalance;
	}
	public void setLocalBalance(BankAccount localBalance) {
		this.localBalance = localBalance;
	}

}