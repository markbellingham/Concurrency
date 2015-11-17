package assignmentThreads;

public class Card extends Thread {
	
	//Create variables
	public static int transactionAmount;
	private int cardId;
	private BankAccount account;
	private int localBalance;
	private int cardBalance;
	static String type;
	
	public Card(int cardId, BankAccount account, int localBalance) {
		this.setCardId(cardId);
		this.account = account;
		this.localBalance = localBalance;
	}
	
	
	// run() is started by the start() method in the main class
	@Override
	public void run() {		
		
		try {
			//Each card makes 20 transactions
			for (int i = 0; i < 20; i++) {
				if (Math.random() > 0.5) {
					//Synchronised makes this set of instructions happen as a single atomic action
					synchronized(account) {
						transactionAmount = ((int) (Math.random()*10));
						account.withdraw(transactionAmount);
						type = "Withdrawal";
						account.call(cardId, account);
						cardBalance -= transactionAmount;
					}
				} else {
					synchronized(account) {
						transactionAmount = ((int) (Math.random()*10));
						account.deposit(transactionAmount);
						type = "Deposit";
						account.call(cardId, account);
						cardBalance += transactionAmount;
					}
				}
				//Makes the thread pause for 0.2 seconds which helps to interrupt the flow of the threads
				sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Prints the balance of transactions for each card
		System.out.println("THREAD " + getId() + "    Total Transaction amount: " + cardBalance);
		
	}

	
	//Getters and Setters for cardId and localBalance
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getLocalBalance() {
		return localBalance;
	}
	public void setLocalBalance(int localBalance) {
		this.localBalance = localBalance;
	}

}
