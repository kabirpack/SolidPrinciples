package BankATM.TransactionModes.Implementation;

import BankATM.Bank.Implementation.XyzBank;
import BankATM.Card.Implementation.CreditCard;
import BankATM.Card.Implementation.DebitCard;
import BankATM.TransactionModes.Interface.IAmountValidator;
import BankATM.TransactionModes.Interface.ICreditCardTransaction;
import BankATM.TransactionModes.Interface.IDebitCardTransaction;
import BankATM.TransactionModes.Interface.ITax;

public class XyzAtm implements ICreditCardTransaction, IDebitCardTransaction, IAmountValidator, ITax {

    private XyzBank bank;

    private int amtValidator = 5;

    public XyzAtm(XyzBank bank) {
        this.bank = bank;
    }

    @Override
    public void withDraw(CreditCard creditCard, int pin, double amount) {
        if(creditCard.getPin() == pin){
            double tax = this.getTax(amount);
            if(isAmountValid(amount)){
                System.out.println("please correct the amount of value.It Should be multiple of USD 5");
            }else if(((creditCard.getBalance()+ tax ) < amount) || (creditCard.getBalance() < bank.getMinimumBalance())){
                System.out.println("Insufficient funds, Current balance is " + creditCard.getBalance());
            }else{
                creditCard.debit(amount + tax );
                System.out.println("The transaction of USD" + amount + " complete with tax USD" + tax + " and the current balance is USD" + creditCard.getBalance());
            }
        }else{
            System.out.println("Enter Correct pin");
        }
    }

    @Override
    public void deposit(CreditCard creditCard, int pin, double amount) {
        if(creditCard.getPin() == pin){
            creditCard.deposit(amount);
            System.out.println("Amount deposited successfully, current baance " + creditCard.getBalance());
        }else{
            System.out.println("Enter Correct pin");
        }
    }

    @Override
    public void showBalance(CreditCard creditCard, int pin) {
        if(creditCard.getPin() == pin){
            System.out.println("ACCOUNT NAME :" + creditCard.getName());
            System.out.println("ACCOUNT NUMBER :" + creditCard.getNumber());
            System.out.println("YOUR ACCOUNT BALANCE FOR YOUR ACCOUNT IS: USD " + creditCard.getBalance() );
        }else{
            System.out.println("Enter Correct pin");
        }

    }

    @Override
    public void withDraw(DebitCard debitCard, int pin, double amount) {
        if(debitCard.getPin() == pin){
            double tax = this.getTax(amount);
            if(isAmountValid(amount)){
                System.out.println("please correct the amount of value.It Should be multiple of USD 5");
            }else if(((debitCard.getBalance()+ tax ) < amount) || (debitCard.getBalance() < bank.getMinimumBalance())){
                System.out.println("Insufficient funds, Current balance is " + debitCard.getBalance());
            }else{
                debitCard.debit(amount + tax );
                System.out.println("The transaction of USD" + amount + " complete with tax USD" + tax + " and the current balance is USD" + debitCard.getBalance());
            }
        }else{
            System.out.println("Enter Correct pin");
        }


    }

    @Override
    public void deposit(DebitCard debitCard, int pin, double amount) {
        if(debitCard.getPin() == pin){
            debitCard.deposit(amount);
            System.out.println("Amount deposited successfully, current baance " + debitCard.getBalance());
        }else{
            System.out.println("Enter Correct pin");
        }
    }


    @Override
    public void showBalance(DebitCard debitCard, int pin) {
        if(debitCard.getPin() == pin){
            System.out.println("ACCOUNT NAME :" + debitCard.getName());
            System.out.println("ACCOUNT NUMBER :" + debitCard.getNumber());
            System.out.println("YOUR ACCOUNT BALANCE FOR YOUR ACCOUNT IS: USD " + debitCard.getBalance() );
        }else{
            System.out.println("Enter Correct pin");
        }
    }

    @Override
    public void setAmountValidationParameter(int divisor) {
        this.amtValidator = divisor;
    }

    @Override
    public int getAmtValidator() {
        return amtValidator;
    }

    @Override
    public boolean isAmountValid(double amount) {
        if(amount % this.amtValidator == 0){
            return true;
        }
        return false;
    }


    @Override
    public double getTax(double amount) {
        double tax = amount<=bank.getMinimumBalance() ? ((amount/100)* bank.getBasicTaxPercent()) :((amount/100) * bank.getPremiumTaxPercent());
        return tax;
    }
}

