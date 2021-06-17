package BankATM.TransactionModes.Implementation;

import BankATM.Bank.Implementation.XyzBank;
import BankATM.Card.Implementation.CreditCard;
import BankATM.Card.Implementation.DebitCard;
import BankATM.TransactionModes.Interface.IAmountValidator;
import BankATM.TransactionModes.Interface.ICreditCardSwiper;
import BankATM.TransactionModes.Interface.IDebitCardSwiper;
import BankATM.TransactionModes.Interface.ITax;

public class XyzSwiper implements ICreditCardSwiper, IDebitCardSwiper, ITax, IAmountValidator {

    private int amtValidator;
    private XyzBank bank;

    public XyzSwiper(XyzBank bank) {
        this.bank = bank;
    }

    @Override
    public void swipe(CreditCard creditCard, int pin, double amount) {
        if(creditCard.getPin() == pin){
            double tax = this.getTax(amount);
            if(isAmountValid(amount)){
                System.out.println("please correct the amount of value.It Should be multiple of USD 5");
            }else if(((creditCard.getBalance()+ tax ) < amount) || (creditCard.getBalance() < bank.getMinimumBalance())){
                System.out.println("Insufficient funds, Current balance is " + creditCard.getBalance());
            }else{
                creditCard.debit(amount + tax );
                creditCard.cashBack((amount/ bank.getMinimumBalance())* bank.getCashBackPercent());
                System.out.println("The transaction of USD" + amount + " complete with tax USD" + tax + " and the current balance is USD" + creditCard.getBalance());
            }
        }else{
            System.out.println("Enter Correct pin");
        }

    }

    @Override
    public void swipe(DebitCard debitCard, int pin, double amount) {
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
