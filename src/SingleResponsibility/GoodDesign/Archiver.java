package SingleResponsibility.GoodDesign;
import java.util.ArrayList;

public class Archiver {
    ArrayList<Invoice> invArr = new ArrayList<>();

    public Archiver() {
    }

    public void archiveInvoice(Invoice invoice){
        invArr.add(invoice);
    }
}
