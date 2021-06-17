package BankATM.Accounts.Implementation;

import BankATM.Accounts.Interface.IAccount;
import BankATM.Accounts.Interface.ICreateCard;
import BankATM.Card.Implementation.CreditCard;
import BankATM.Card.Implementation.DebitCard;

public class XyzAccount extends ICreateCard implements IAccount{

    private String name;
    private long number;
    private double balance;
    private DebitCard debitcard;
    private CreditCard creditCard;

    public XyzAccount(String name, long number) {
        this.name = name;
        this.number = number;
        this.balance = 100.00;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public void debit(double amount) {
        this.balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void cashBack(double amount) {
        this.balance += amount;
    }

    @Override
    public CreditCard createCreditCard(long cardNumber, String validFrom, String validTo, int cvv, int pin) {
        this.creditCard = new CreditCard(this,cardNumber,validFrom,validTo,cvv,pin);
        return creditCard;
    }

    @Override
    public DebitCard createDebitCard(long cardNumber, String validFrom, String validTo, int cvv, int pin) {
        this.debitcard = new DebitCard(this,cardNumber,validFrom,validTo,cvv,pin);
        return debitcard;
    }
}
