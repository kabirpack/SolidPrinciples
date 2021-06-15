package SingleResponsibility.GoodDesign;

public class Mailer {
    public void emailInvoice(String mailBody, String mailAddress){
        System.out.println("Sending email of" + mailBody + "to " + mailAddress);
    }
}
