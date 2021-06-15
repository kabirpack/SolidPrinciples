package LiskovsPrinciple.GoodDesign;

public abstract class Employee implements IEmployee, IEmployeeBonus {
    public int id;
    public String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract double calculateBonus (double salary);

    public String getName() {
        return name;
    }
}
