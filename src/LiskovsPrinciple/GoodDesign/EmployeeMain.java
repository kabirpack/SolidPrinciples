package LiskovsPrinciple.GoodDesign;

import java.util.ArrayList;

public class EmployeeMain {
    public static void main(String[] args){
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new PermanentEmployee(1,"viki"));
        employees.add(new TemporaryEmployee(2,"helen"));
        //employees.add(new ContractEmployee(1,"sindhu"));

        for (Employee employee : employees){
            System.out.println(employee.getName() + " has a bonus of " + employee.calculateBonus(40000.00));
        }

        ArrayList<IEmployee> employees1 = new ArrayList<>();
        employees1.add(new PermanentEmployee(1,"viki"));
        employees1.add(new TemporaryEmployee(2,"helen"));
        employees1.add(new ContractEmployee(1,"sindhu"));

        for (IEmployee employee : employees1){
            System.out.println(employee.getName() + " has basic pay of " + employee.getMinimumSalary());
        }


    }
}
