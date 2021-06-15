package SingleResponsibility.GoodDesign;

public class Invoice {
    private String name;
    private String phoneNumber;
    private int amount;

    public Invoice(String name, String phoneNumber, int amount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAmount() {
        return amount;
    }

}
