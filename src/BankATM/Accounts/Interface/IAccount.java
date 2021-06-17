package BankATM.Accounts.Interface;

public interface IAccount{
    double getBalance();
    String getName();
    long getNumber();
    void debit(double amount);
    void deposit(double amount);
    void cashBack(double amount);
}
