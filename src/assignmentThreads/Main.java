package assignmentThreads;

public class Main {
	
	private BankAccount localBalance;

	public static void main(String[] args) {
		
		if (args.length == 2) {
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
				Card j = new Card(account, localBalance);
				j.start();
				//Add threads to the array so that they can be joined later
				threadArray[i] = j;			
			}
			
			//The join method in this loop ensures that the program will
			//wait for all threads to complete before printing the statement
			for (int i = 1; i < threadArray.length; i++) {
				try {
					threadArray[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
						
			System.out.println();
			System.out.println("Threads complete");
			System.out.println();
			System.out.println("Opening Balance: " + localBalance + "\tCards Used: " + numberOfCards);
			System.out.println();
			//Prints the formatted headers for the statement
			System.out.println("Transaction\t|\tWithdrawal\t|\tDeposit\t\t|\tBalance");
			System.out.println("-------------------------------------------------------------------------------");
			//Print all the transactions
			BankAccount.print();			
		} else {
			//If the user tries to run the program with anything other than 2 arguments
			//The program will exit and display the following messages
			System.out.println("Error: incorrect usage");
			System.out.println("Usage: java assignmentThreads/Main [number of cards] [opening balance]");
		}

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