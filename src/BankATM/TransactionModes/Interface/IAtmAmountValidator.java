package BankATM.TransactionModes.Interface;

import java.util.ArrayList;

public interface IAtmAmountValidator {
    ArrayList<Double> getAvailableDenomination();
    void addNewDenomination();
    boolean isValidDenomination(double amount);
}
