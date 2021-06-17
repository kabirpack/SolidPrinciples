package BankATM.Bank.Implementation;

import BankATM.Accounts.Implementation.XyzAccount;
import BankATM.Bank.Interface.*;
import BankATM.TransactionModes.Implementation.XyzAtm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class XyzBank implements IBank, IAccountDetails, ICardDetails, ITaxCalculator, IShoppingCharges,ISetupATM {

    private String name;
    private final int basicTaxPercent = 2;
    private final int premiumTaxPercent = 4;
    private final double minimumBalance = 100.00;
    private final int denomination = 5;
    private final int cashBackPercent = 1;
    private XyzAtm atm;
    private LocalDate fromDateObj;
    private LocalDate toDateObj;
    private final DateTimeFormatter cardDateFormat = DateTimeFormatter.ofPattern("MM-yyyy");
    private ArrayList<XyzAccount> bankAccounts = new ArrayList<XyzAccount>();

    public XyzBank(String name) {
        this.name = name;
    }


    @Override
    public long generateAccountNumber() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    @Override
    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public void setCardType() {

    }

    @Override
    public String getCardType() {
        return null;
    }

    @Override
    public long generateCardNumber() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    @Override
    public String generateValidFrom() {
        fromDateObj = LocalDate.now();
        return fromDateObj.format(cardDateFormat);
    }

    @Override
    public String generateValidTo() {
        toDateObj = this.fromDateObj.plusYears(10);
        return toDateObj.format(cardDateFormat);
    }

    @Override
    public int generateCVV() {
        return 100 + new Random().nextInt(900);
    }

    @Override
    public int generateSecretPin() {
        return 1000 + new Random().nextInt(9000);
    }

    @Override
    public XyzAtm setupAtm() {
        this.atm = new XyzAtm(this);
        return atm;
    }

    @Override
    public int getShpBasicCharges() {
        return 0;
    }

    @Override
    public int getShpPremiumCharges() {
        return 0;
    }

    @Override
    public int getCashBackPercent() {
        return cashBackPercent;
    }

    @Override
    public void setBasicTaxPercent() {

    }

    @Override
    public void setPremiumTaxPercent() {

    }

    @Override
    public int getBasicTaxPercent() {
        return basicTaxPercent;
    }

    @Override
    public int getPremiumTaxPercent() {
        return premiumTaxPercent;
    }

    @Override
    public void addAccount(Object o) {

    }

    @Override
    public boolean checkAccount(Object o) {
        return false;
    }

    @Override
    public void removeAccount(Object o) {

    }
}
