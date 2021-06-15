package OpenClosedPrinciple.GoodDesign;

public class PermanantEmployee extends Employee {

    public PermanantEmployee(int id, String name) {
        super(id, name);
    }

    @Override
    public double calculateBonus(double salary) {
        return (salary/100)*25;
    }
}
