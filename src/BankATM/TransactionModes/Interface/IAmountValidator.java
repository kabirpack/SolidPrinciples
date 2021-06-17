package BankATM.TransactionModes.Interface;

public interface IAmountValidator {
    void setAmountValidationParameter(int divisor);
    int getAmtValidator();
    boolean isAmountValid(double amount);
}
