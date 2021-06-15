package LiskovsPrinciple.GoodDesign;

public class ContractEmployee implements IEmployee {
    public int id;
    public String name;

    public ContractEmployee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public double getMinimumSalary() {
        return 5000.00;
    }

    public String getName() {
        return name;
    }
}
