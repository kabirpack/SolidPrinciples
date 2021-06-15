package OpenClosedPrinciple.BadDesign;

public class EmployeeMain {
    public static void main(String[] args){
        OpenClosedPrinciple.BadDesign.Employee employee1 = new OpenClosedPrinciple.BadDesign.Employee(1,"Kumar","Permanent");
        OpenClosedPrinciple.BadDesign.Employee employee2 = new OpenClosedPrinciple.BadDesign.Employee(1,"Raju","Temporary");

        System.out.println(employee1.getName() + " has a bonus of " + employee1.calculateBonus(40000.00));
        System.out.println(employee2.getName() + " has a bonus of " + employee2.calculateBonus(40000.00));

    }
}
