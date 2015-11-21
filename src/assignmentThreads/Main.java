package assignmentThreads;

public class Main {
	
	private BankAccount localBalance;

	public static void main(String[] args) {
		
		//Program is run with two arguments
		//args[0] is the number of cards that have access, args[1] is the opening balance
		int numberOfCards = Integer.parseInt(args[0]);
		int localBalance = Integer.parseInt(args[1]);
		
		//Instantiates the account
		BankAccount account = new BankAccount(localBalance);
		
		//Create array for the threads
		Card threadArray[] = new Card[numberOfCards+1];
		
		//Initialises each card and starts the associated thread
		for (int i = 1; i <= numberOfCards; i++) {
			Card j = new Card(account, localBalance); //Creates a new instance of Card			
			j.start();
			//Add threads to the array
			threadArray[i] = j;			
		}
		
		//This loop ensures that the rest of the program will wait for all threads to complete
		for (int i = 1; i < threadArray.length; i++) {
			try {
				threadArray[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Prints the headers for the statement
		System.out.println();
		System.out.println("Transaction\t|\tWithdrawal\t|\tDeposit\t\t|\tBalance");
		System.out.println("-------------------------------------------------------------------------------");
		BankAccount.print();
	  }
	
	
	//Constructor for Bank Account
	public Main(BankAccount localBalance) {
		this.setLocalBalance(localBalance);
	}
	
	
	
	//Getters and Setters for local balance
	public BankAccount getLocalBalance() {
		return localBalance;
	}
	public void setLocalBalance(BankAccount localBalance) {
		this.localBalance = localBalance;
	}
	

}