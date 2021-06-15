package SingleResponsibility.GoodDesign;

public class SalesTax {
    public final int basicTax = 20;
    public final int premiumTax = 40;

    public SalesTax() {
    }

    public int determineTax(int amount){
        if(amount < 100){
            return amount + basicTax;
        }else{
            return amount + premiumTax;
        }
    }

}
