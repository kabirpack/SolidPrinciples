package BankATM.Card.Implementation;

import BankATM.Accounts.Implementation.XyzAccount;

public class CreditCard{
    private XyzAccount account;
    private long number;
    private String validFrom;
    private String validTo;
    private int cvv;
    private int pin;

    public CreditCard(XyzAccount account, long number, String validFrom, String validTo, int cvv, int pin) {
        this.account = account;
        this.number = number;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.cvv = cvv;
        this.pin = pin;
    }

    public double getBalance() {
        return account.getBalance();    }

    public String getName() {
        return account.getName();
    }

    public long getNumber() {
        return account.getNumber();
    }
    public int getPin() {
        return pin;
    }

    public void debit(double amount) {
        account.debit(amount);
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }
    public void cashBack(double amount) {
        account.cashBack(amount);
    }
}
