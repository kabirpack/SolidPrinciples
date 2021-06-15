package SingleResponsibility.GoodDesign;

public class Printer {
    public void printInvoice(Invoice invoice){
        System.out.println("Customer name "+ invoice.getName()+"\n"+"Customer number "+invoice.getPhoneNumber()+"\n"+"Invoice Amount"+invoice.getAmount()+"\n");
    }

}
