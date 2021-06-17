package BankATM.TransactionModes.Interface;

import BankATM.Card.Implementation.DebitCard;

public interface IDebitCardTransaction {
    void withDraw(DebitCard debitCard, int pin, double amount);
    void deposit(DebitCard debitCard, int pin, double amount);
    void showBalance(DebitCard debitCard, int pin);

}
