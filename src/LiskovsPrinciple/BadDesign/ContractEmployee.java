package LiskovsPrinciple.BadDesign;



public class ContractEmployee extends Employee{

    public ContractEmployee(int id, String name) {
        super(id, name);
    }

    @Override
    public double calculateBonus(double salary) {
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
