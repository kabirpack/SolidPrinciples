package OpenClosedPrinciple.GoodDesign;

public class EmployeeMain {
    public static void main(String[] args){
        Employee employee1 = new OpenClosedPrinciple.GoodDesign.PermanantEmployee(1,"Kumar");
        Employee employee2 = new TemporaryEmployee(1,"Raju");

        System.out.println(employee1.getName() + " has a bonus of " + employee1.calculateBonus(40000.00));
        System.out.println(employee2.getName() + " has a bonus of " + employee2.calculateBonus(40000.00));

    }

}
