package BankATM.TransactionModes.Interface;

import BankATM.Card.Implementation.CreditCard;

public interface ICreditCardTransaction {
    void withDraw(CreditCard creditCard, int pin, double amount);
    void deposit(CreditCard creditCard, int pin,double amount);
    void showBalance(CreditCard debitCard, int pin);
}
