package BankATM.TransactionModes.Interface;

import BankATM.Card.Implementation.DebitCard;

public interface IDebitCardSwiper {
    void swipe(DebitCard debitCard, int pin, double amount);
}
