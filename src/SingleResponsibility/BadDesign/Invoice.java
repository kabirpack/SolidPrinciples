package SingleResponsibility.BadDesign;

import java.util.ArrayList;

public class Invoice {
    public final int basicTax = 20;
    public final int premiumTax = 40;
    private String name;
    private String phoneNumber;
    private int amount;

    public Invoice(String name, String phoneNumber, int amount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public void emailInvoice(String mailBody, String mailAddress){
        System.out.println("Sending email of" + mailBody + "to " + mailAddress);
    }
    public int determineTax(int amount){
        if(amount < 100){
            return amount + basicTax;
        }else{
            return amount + premiumTax;
        }
    }
    public void archiveInvoice(Invoice invoice){
        ArrayList<Invoice> invArr = new ArrayList<>();
        invArr.add(invoice);
    }
    public void printInvoice(){
        System.out.println("Customer name "+ name+"\n"+"Customer number "+phoneNumber+"\n"+"Invoice Amount"+amount+"\n");
    }
}
