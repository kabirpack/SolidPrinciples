package BankATM.TransactionModes.Interface;

import BankATM.Card.Implementation.CreditCard;

public interface ICreditCardSwiper {
    void swipe(CreditCard creditCard, int pin, double amount);
}
