package assignmentThreads;

import java.util.ArrayList;

public class Card extends Thread {
	
	//Create variables
	private BankAccount account;
	private int transactionAmount;
	private int localBalance;
	private int cardBalance;
	
	private static ArrayList<Integer[]> transaction = new ArrayList<Integer[]>();
	
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
						localBalance = account.withdraw(transactionAmount);
						cardBalance -= transactionAmount;
						Integer[] array = {(int)getId(),transactionAmount,0,localBalance};
						getTransaction().add(array);
					}
				} else {
					synchronized(account) {
						transactionAmount = ((int) (Math.random()*10));
						localBalance = account.deposit(transactionAmount);
						cardBalance += transactionAmount;
						Integer[] array = {(int)getId(),0,transactionAmount,localBalance};
						getTransaction().add(array);
					}
				}
				//Makes the thread pause for 0.2 seconds which helps to interrupt the flow of the threads
				sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Prints the balance of transactions for each card
		System.out.println("THREAD " + getId() + "    Card transaction amount: " + cardBalance);		
	}
	

	
	//Getters and Setters for localBalance and transaction
	public int getLocalBalance() {
		return localBalance;
	}
	public void setLocalBalance(int localBalance) {
		this.localBalance = localBalance;
	}
	public static ArrayList<Integer[]> getTransaction() {
		return transaction;
	}


}
