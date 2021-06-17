package BankATM.Accounts.Interface;

public abstract class ICreateCard<C> {
    public abstract C createCreditCard(long cardNumber, String validFrom, String validTo, int cvv, int pin);
    public abstract C createDebitCard(long cardNumber, String validFrom, String validTo, int cvv, int pin);
}
