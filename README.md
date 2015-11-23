# Concurrency
Demonstrating concurrency with synchronised threads

This program simulates a bank account to which more than one card has access. The aim is to prevent a race condition caused by two cards trying to access the account at precisely the same time, which can lead to unpredictable results and subtle program bugs. Each access card is implemented as a separate thread and will have 20 transactions with the account. Thus if you create 5 cards, you will have 100 transactions. The account must always have a positive balance and if a card/thread tries to withdraw when there is not enough money in the account, it will be forced to wait. This could potentially lead to deadlock if the account balance is low and all cards try to withdraw. For this assignment we were not required to implement deadlock prevention.

When all threads have completed, the program will first display the balance of transactions for each thread, with withdrawals being positive and deposits being negative. It will then output a statement of each transaction, showing the transaction number, the thread number (in brackets), either the deposit or withdrawal amount and the running balance.

The program takes two arguments, the first being how many cards you want to grant access and the second is the opening balance. If you don't provide two arguments, the program will quit with an error message.

To run it you must first compile it by:

javac assignmentThreads/Main.java

Then simply run:

java assignmentThreads/Main [number of cards] [opening balance]

You may wish to remove the package line at the top of each file, then run the program using:

javac Main.java

java Main [number of cards] [opening balance]


Any questions / feedback: mark.bellingham@protonmail.ch

Program source code:
https://github.com/markbellingham/Concurrency
