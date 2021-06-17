package BankATM.Bank.Interface;

public interface IBank<Account> {
    void addAccount(Account account);
    boolean checkAccount(Account account);
    void removeAccount(Account account);
}
