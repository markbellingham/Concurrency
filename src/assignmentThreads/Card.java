package assignmentThreads;

public class Card extends Thread {
	
	//Create variables
	private BankAccount account;
	private int transactionAmount;
	private int localBalance;
	private int cardBalance;
	String type;
	//This variable is static because the value needs to carry through each thread
	private static int transactionNumber = 1;
	
	//Constructor for Card
	public Card(BankAccount account, int localBalance) {
		this.account = account;
		this.localBalance = localBalance;
	}
	
	
	// run() method is started by the start() method in the main class
	@Override
	public void run() {
		
		try {
			//Each card makes 20 transactions
			for (int i = 0; i < 20; i++) {
				if (Math.random() > 0.5) {
					//Synchronised makes this whole set of instructions happen as a single atomic action
					synchronized(account) {
						transactionAmount = ((int) (Math.random()*10));
						account.withdraw(transactionAmount);
						type = "Withdrawal";
						account.print(transactionNumber, account, type, transactionAmount);
						cardBalance -= transactionAmount;
						transactionNumber++;
					}
				} else {
					synchronized(account) {
						transactionAmount = ((int) (Math.random()*10));
						account.deposit(transactionAmount);
						type = "Deposit";
						account.print(transactionNumber, account, type, transactionAmount);
						cardBalance += transactionAmount;
						transactionNumber++;
					}
				}
				//Makes the thread pause for 0.2 seconds which helps to interrupt the flow of the threads
				sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Prints the balance of transactions for each card, starting on a new line
		System.out.println();
		System.out.println("THREAD " + getId() + "    Total Transaction amount: " + cardBalance);		
	}
	

	
	//Getters and Setters for localBalance
	public int getLocalBalance() {
		return localBalance;
	}
	public void setLocalBalance(int localBalance) {
		this.localBalance = localBalance;
	}

}
