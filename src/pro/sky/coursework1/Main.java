package pro.sky.coursework1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = new Employee[10];

        Employee employee = new Employee("Mark Adams", Employee.Departments.FINANCE,1200.05);
        employees[0] = employee;
        employee = new Employee("Sarah Thompson", Employee.Departments.IT,1505.35);
        employees[1] = employee;
        employee = new Employee("Tim Johns", Employee.Departments.SALES,133.2);
        employees[2] = employee;
        employee = new Employee("Muhammed Abbas", Employee.Departments.SALES,100.00);
        employees[3] = employee;
        employee = new Employee("Carl Marks", Employee.Departments.SALES,2000.00);
        employees[4] = employee;
        employee = new Employee("Bruce Lee", Employee.Departments.FINANCE,2005.00);
        employees[5] = employee;
        employee = new Employee("Andrew Lincoln", Employee.Departments.IT,3000.00);
        employees[6] = employee;
        employee = new Employee("Norman Reedus", Employee.Departments.IT,4000.00);
        employees[7] = employee;

        System.out.println("a."); //Получить список всех сотрудников со всеми имеющимися по ним данными.
        printAllEmployees(employees);

        System.out.println("b."); //Посчитать сумму затрат на зарплаты в месяц.
        System.out.printf("\tSalary sum of all employees = %.2f\n", sumSalary(employees));

        System.out.println("c."); //Найти сотрудника с минимальной зарплатой.
        System.out.println("\tEmployee with min salary: " + findEmployeeWithMinSalary(employees));

        System.out.println("d."); //Найти сотрудника с максимальной зарплатой.
        System.out.println("\tEmployee with max salary: " + findEmployeeWithMaxSalary(employees));

        System.out.println("e."); //Подсчитать среднее значение зарплат (можно использовать для этого метод из пункта b).
        System.out.printf("\tMedian salary: %.2f\n", calculateMedianSalary(sumSalary(employees),getSize(employees)));

        System.out.println("f."); //Получить Ф. И. О. всех сотрудников (вывести в консоль).
        printNamesOnly(employees);

//        Повышенная сложность
        System.out.println("****************************Additional Tasks:");
        System.out.println("1."); //Проиндексировать зарплату (вызвать изменение зарплат у всех сотрудников на величину аргумента в %).
        incSalary(employees,5);
        printAllEmployees(employees);

        System.out.println("2."); //Получить в качестве параметра номер отдела (1–5) и найти (всего 6 методов):

        // вот у нас есть отдельно отдел и его теперь можно поставить в методы которые мы уже написали
        Employee[] employeesInDepartment = getDepartment(employees,1);
        printAllEmployees(employeesInDepartment);

        System.out.println("a."); //Найти сотрудника с минимальной зарплатой.
        System.out.println("\tEmployee with min salary: " + findEmployeeWithMinSalary(employeesInDepartment));

        System.out.println("b."); //Найти сотрудника с максимальной зарплатой.
        System.out.println("\tEmployee with max salary: " + findEmployeeWithMaxSalary(employeesInDepartment));

        System.out.println("c."); //Сумму затрат на зарплату по отделу.
        System.out.printf("\tSalary sum of all employees = %.2f\n", sumSalary(employeesInDepartment));

        System.out.println("d."); //Среднюю зарплату по отделу (учесть, что количество людей в отделе отличается от employees.length).
        System.out.printf("\tMedian salary: %.2f\n", calculateMedianSalary(sumSalary(employeesInDepartment),getSize(employeesInDepartment)));

        System.out.println("e."); //Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра.
        incSalary(employeesInDepartment,10);
        printAllEmployees(employeesInDepartment);

        System.out.println("f."); //Напечатать всех сотрудников отдела (все данные, кроме отдела).
        printDepartmentEmployees(employeesInDepartment);

        System.out.println("3."); //Получить в качестве параметра число и найти:

        System.out.println("a."); //Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
        printAllWithSalaryBelow(employees, 1000.00);

        System.out.println("b."); //Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
        printAllWithSalaryAbove(employees, 2000.00);

    }

    public static void printAllEmployees(Employee[] employees) {

        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return;
        }

        for(Employee employee1:employees){
            if(employee1!=null){
                System.out.println("\t"+employee1);
            }
        }
    }

    public static double sumSalary(Employee[] employees) {

        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return 0.0;
        }

//        1. Classic solution
        double sum = 0;
        for(Employee employee:employees){
            if(employee!=null){
                sum += employee.getSalary();
            }
        }
        return sum;

//        2. Lambda streams solution  Самостоятельно пытаюсь понять лямбда и стримы и пробую закрепить на практике
//        return Arrays.stream(employees)
//                .filter(employee -> employee!=null)
//                .mapToDouble(employee-> employee.getSalary())
//                .sum();

//        3. Lambda streams solution with method reference
//            return Arrays.stream(employees)
//                .filter(Objects::nonNull)
//                .mapToDouble(Employee::getSalary)
//                .sum(); //

    }

    public static Employee findEmployeeWithMinSalary(Employee[] employees) {

        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return null;
        }

//      1. Classic solution
        Employee employee = null;
        double minSalary = employees[0].getSalary();  //Берем зарплату первого работника и будем с ней сравнивать.
        for (Employee employee1:employees){
            if(employee1!=null){
                if(employee1.getSalary()<=minSalary){
                    minSalary = employee1.getSalary();
                    employee = employee1;
                }
            }
        }
        return employee;

//       2. Solution with lambda
//        Comparator<Employee> salaryCompare = (employee1,employee2) -> {
//            if(employee1.getSalary()<employee2.getSalary()){
//                return -1;
//            } else if(employee1.getSalary()>employee2.getSalary()){
//                return 1;
//            }
//            return 0;
//        };
//        return Arrays.stream(employees).filter(Objects::nonNull).min(salaryCompare).get();

    }

    public static Employee findEmployeeWithMaxSalary(Employee[] employees) {

        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return null;
        }

//        1. Classic solution
        Employee employee = null;
        double maxSalary = 0.00;
        for (Employee employee1:employees){
            if(employee1!=null){
                if(employee1.getSalary()>=maxSalary){
                    maxSalary = employee1.getSalary();
                    employee = employee1;
                }
            }
        }
        return employee;

//       2. Solution with lambda
//        Comparator<Employee> salaryCompare = (employee1,employee2) -> {
//            if(employee1.getSalary()<employee2.getSalary()){
//                return -1;
//            } else if(employee1.getSalary()>employee2.getSalary()){
//                return 1;
//            }
//            return 0;
//        };
//        return Arrays.stream(employees).filter(Objects::nonNull).max(salaryCompare).get();

    }

    public static double calculateMedianSalary(Double salarySum, int getSize) {
        return salarySum/getSize;
    }

    public static void printNamesOnly(Employee[] employees) {
        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return;
        }

        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .forEach(employee -> System.out.println("\t"+employee.getName()));
    }

    // Метод getSize я сначала сделал как boolean isEmpty, но в потом подумал и решил возвращать int так как нам надо будет еще найти среднее значение зарплат и тут нам size пригодится
    public static int getSize (Employee[] employees){
        int counter = 0;
        if(employees==null) return counter;
        for(Employee employee:employees){
            if(employee!=null){
                counter++;
            }
        }
        return counter;
    }

    public static void incSalary(Employee[] employees, int percent) {
        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return;
        }
        for (Employee e:employees){
            if(e!=null){
                e.setSalary(e.getSalary() + (e.getSalary()*percent)/100);
            }
        }
    }

// Для того чтобы работать с каждым отделом по отдельности будем работников отдела записывать в отдельный массив и его возвращать этим методом
    public static Employee[] getDepartment(Employee[] employees, int departmentN) {
        Employee.Departments department = convertIntToEnum(departmentN);

//        находим кол во сотрудников в отделе чтобы знать размер массива который будем создавать
        int length = 0;
        for(Employee e:employees){
            if (e!=null&&e.getDepartment().equals(department)){
                length++;
            }
        }
        if (length == 0){
            return null;
        }

        Employee[] employeesInDepartment = new Employee[length];
        int i = 0;
        for(Employee e:employees){
            if (e!=null&&e.getDepartment().equals(department)){
                employeesInDepartment[i] = e;
                i++;
            }
        }

        return employeesInDepartment;
    }

    public static void printDepartmentEmployees(Employee[] employees) {
        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return;
        }

        Arrays.stream(employees).forEach(e-> System.out.println(String.format("\tid:" + e.getId() + " Name: " + e.getName()  + "\tSalary: %.2f $",e.getSalary())));
    }

//    Так как я вместо номеров отделов использовал Enum а в задании надо использовать номер, чтобы работать с отделами, то делаю конвертацию номера в Enum
    public static Employee.Departments convertIntToEnum(int x) {
        switch (x) {
            case 1: return Employee.Departments.IT;
            case 2: return Employee.Departments.FINANCE;
            case 3: return Employee.Departments.HR;
            case 4: return Employee.Departments.SALES;
            case 5: return Employee.Departments.MARKETING;
            default: return null;
        }
    }

    public static void printAllWithSalaryBelow (Employee[] employees, Double salary){
        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return;
        }
        for(Employee e:employees){
            if(e!=null&&e.getSalary()<salary){
                System.out.println(String.format("\tid:" + e.getId() + " Name: " + e.getName()  + "\tSalary: %.2f $",e.getSalary()));
            }
        }
    }

    public static void printAllWithSalaryAbove (Employee[] employees, Double salary){
        if(employees==null||getSize(employees)==0){
            System.out.println("Error. No employees found!");
            return;
        }
        for(Employee e:employees){
            if(e!=null&&e.getSalary()>=salary){
                System.out.println(String.format("\tid:" + e.getId() + " Name: " + e.getName()  + "\tSalary: %.2f $",e.getSalary()));
            }
        }
    }
}