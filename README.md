# Concurrency
Demonstrating concurrency with synchronised threads

This program simulates a bank account to which more than one card has access. The aim is to prevent a race condition caused by two cards trying to access the account at precisely the same time, which can lead to unpredictable results and subtle program bugs.

The program takes two arguments, the first being how many cards you want to grant access and the second is the opening balance.

To run it you must first compile it by:

javac assignmentThreads/Main.java

Then simply run:

java assignmentThreads/Main [number of cards] [opening balance]

You may wish to remove the package line at the top of each file, then run the program from the directory where it is saved using:

javac Main.java

java Main [number of cards] [opening balance]


Any questions / feedback: mark.bellingham@protonmail.ch

Program source code:
https://github.com/markbellingham/Concurrency
