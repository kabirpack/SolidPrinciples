package LiskovsPrinciple.GoodDesign;

public class PermanentEmployee extends Employee{
    public PermanentEmployee(int id, String name) {
        super(id, name);
    }

    @Override
    public double calculateBonus(double salary) {
        return (salary/100)*25;
    }

    @Override
    public double getMinimumSalary() {
        return 40000.00;
    }
}
