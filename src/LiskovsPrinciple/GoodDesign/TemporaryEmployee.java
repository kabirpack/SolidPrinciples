package LiskovsPrinciple.GoodDesign;

public class TemporaryEmployee extends Employee {

    public TemporaryEmployee(int id, String name) {
        super(id, name);
    }

    @Override
    public double calculateBonus(double salary) {
        return (salary/100)*15;
    }

    @Override
    public double getMinimumSalary() {
        return 15000.00;
    }
}
