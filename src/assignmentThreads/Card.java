package assignmentThreads;

public class Card extends Thread {
	
	public static int transactionAmount;
	private int cardId;
	private BankAccount account;
	private int localBalance;
	static String type;
	
	public Card(int cardId, BankAccount account, int currthread) {
		this.setCardId(cardId);
		this.account = account;
	}
	
	
	@Override
	public void run() {
		
		
		try {
			for (int i = 0; i < 20; i++) {
				if (Math.random() > 0.5) {
					synchronized(account) {
						transactionAmount = ((int) (Math.random()*10));
						account.withdraw(transactionAmount);
						type = "Withdrawal";
						account.call(cardId, account);
					}
				} else {
					synchronized(account) {
						transactionAmount = ((int) (Math.random()*10));
						account.deposit(transactionAmount);
						type = "Deposit";
						account.call(cardId, account);
					}
				}
				sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}


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
