package OpenClosedPrinciple.BadDesign;

public class Employee {
    public int id;
    public String name;
    public String employeeType;

    public Employee(int id, String name, String employeeType) {
        this.id = id;
        this.name = name;
        this.employeeType = employeeType;
    }

    public double calculateBonus (double salary){
        if(this.employeeType.equals("Permanent")){  //new feature to check employee type
            return (salary/100)*25;
        }
        return (salary/100)*15;
    }

    public String getName() {
        return name;
    }
}
