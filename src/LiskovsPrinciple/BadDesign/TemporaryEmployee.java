package LiskovsPrinciple.BadDesign;


public class TemporaryEmployee extends Employee {
    public TemporaryEmployee(int id, String name) {
        super(id, name);
    }

    @Override
    public double calculateBonus(double salary) {
        return (salary/100)*15;
    }
}
