package BankATM.Bank.Interface;

public interface ITaxCalculator {
    void setBasicTaxPercent();
    void setPremiumTaxPercent();
    int getBasicTaxPercent();
    int getPremiumTaxPercent();
}
