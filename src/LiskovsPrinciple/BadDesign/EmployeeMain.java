package LiskovsPrinciple.BadDesign;
public class EmployeeMain {

    public static void main(String[] args){
        Employee employee1 = new PermanentEmployee(1,"Kumar");
        Employee employee2 = new TemporaryEmployee(1,"Raju");
        Employee employee3 = new ContractEmployee(3,"Rahul");
        System.out.println(employee1.getName() + " has a bonus of " + employee1.calculateBonus(40000.00));
        System.out.println(employee2.getName() + " has a bonus of " + employee2.calculateBonus(15000.00));
        System.out.println(employee3.getName() + " has a bonus of " + employee3.calculateBonus(5000.00));
    }
}

