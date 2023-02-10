package pro.sky.coursework1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EmployeeBook {

    // Задания из раздела "Очень сложно" находятся в самом низу, после всех перенесенных методов с Main

    private Employee[] employees;

    public EmployeeBook() {
        this.employees = new Employee[10];
    }

    public void printAllEmployees() {

        if(getSize(employees)<=0){  // метод getSize возвращает -1 если массив null и 0 если там нет записей, поэтому проверяем если <= 0
            System.out.println("Error. No employees found!");
            return;
        }

        for(Employee employee1:employees){
            if(employee1!=null){
                System.out.println("\t"+employee1);
            }
        }
    }

    public double sumSalary() {

        if(getSize(employees)<=0){
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

    public Employee findEmployeeWithMinSalary() {

        if(getSize(employees)<=0){
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

    public Employee findEmployeeWithMaxSalary() {

        if(getSize(employees)<=0){
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

    public double calculateMedianSalary() {
        return sumSalary()/getSize(employees);
    }

    public void printNamesOnly() {
        if(getSize(employees)<=0){
            System.out.println("Error. No employees found!");
            return;
        }

        Arrays.stream(employees)
                .filter(Objects::nonNull)
                .forEach(employee -> System.out.println("\t"+employee.getName()));
    }

    // Метод getSize я сначала сделал как boolean isEmpty, но в потом подумал и решил возвращать int так как нам надо будет еще найти среднее значение зарплат и тут нам size пригодится
    private int getSize (Employee[] employees){
        int counter = 0;
        if(employees==null) return -1;
        for(Employee employee:employees){
            if(employee!=null){
                counter++;
            }
        }
        return counter;
    }

    public void incSalary(int percent) {
        if(getSize(employees)<=0){
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
    public Employee[] getDepartment(int departmentN) {
        if(getSize(employees)<=0){
            System.out.println("Error. No employees found!");
            return null;
        }

        Employee.Departments department = convertIntToEnum(departmentN);

        if(department == null) { // если такого отдела нет то выходим и возвращаем null
            return null;
        }

//        находим кол во сотрудников в отделе чтобы знать размер массива который будем создавать
        int length = 0;
        for(Employee e:employees){
            if (e!=null&&e.getDepartment().equals(department)){
                length++;
            }
        }
        if (length == 0){ // если никого не нашли то просто выходим и возвращаем null
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

    // Методы для работы с отделами а не со всем массивом

    public boolean printDepartmentEmployees(Employee[] employeesInDepartment) {
        if(getSize(employeesInDepartment)<=0){
            return false;
        }
        System.out.println(employeesInDepartment[0].getDepartment());
        Arrays.stream(employeesInDepartment).forEach(e-> System.out.println(String.format("\tid:" + e.getId() + " Name: " + e.getName()  + "\tSalary: %.2f $",e.getSalary())));
        return true;
    }

    //    Так как я вместо номеров отделов использовал Enum а в задании надо использовать номер, чтобы работать с отделами, то делаю конвертацию номера в Enum
    public Employee.Departments convertIntToEnum(int x) {
        switch (x) {
            case 1: return Employee.Departments.IT;
            case 2: return Employee.Departments.FINANCE;
            case 3: return Employee.Departments.HR;
            case 4: return Employee.Departments.SALES;
            case 5: return Employee.Departments.MARKETING;
            default: return null;
        }
    }

    public Employee findEmployeeWithMinSalaryForDept(Employee[] employeesInDepartment) {

        if(getSize(employeesInDepartment)<=0){
            System.out.println("Error. No employees found!");
            return null;
        }

        Employee employee = null;
        double minSalary = employeesInDepartment[0].getSalary();  //Берем зарплату первого работника и будем с ней сравнивать.
        for (Employee employee1:employeesInDepartment){
            if(employee1!=null){
                if(employee1.getSalary()<=minSalary){
                    minSalary = employee1.getSalary();
                    employee = employee1;
                }
            }
        }
        return employee;

    }

    public Employee findEmployeeWithMaxSalaryForDept(Employee[] employeesInDepartment) {

        if(getSize(employeesInDepartment)<=0){
            System.out.println("Error. No employees found!");
            return null;
        }

        Employee employee = null;
        double maxSalary = 0.00;
        for (Employee employee1:employeesInDepartment){
            if(employee1!=null){
                if(employee1.getSalary()>=maxSalary){
                    maxSalary = employee1.getSalary();
                    employee = employee1;
                }
            }
        }
        return employee;
    }

    public double calculateMedianSalaryForDept(Employee[] employeesInDepartment) {
        return sumDeptSalary(employeesInDepartment)/getSize(employeesInDepartment);
    }

    public double sumDeptSalary(Employee[] employeesInDepartment) {

        if(getSize(employeesInDepartment)<=0){
            System.out.println("Error. No employees found!");
            return 0.0;
        }

        double sum = 0;
        for(Employee employee:employeesInDepartment){
            if(employee!=null){
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    public void incSalaryForDept(Employee[] employeesInDepartment, int percent) {
        if(getSize(employeesInDepartment)<=0){
            System.out.println("Error. No employees found!");
            return;
        }
        for (Employee e:employeesInDepartment){
            if(e!=null){
                e.setSalary(e.getSalary() + (e.getSalary()*percent)/100);
            }
        }
    }



    // Получить в качестве параметра число и найти:
    //Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).

    public void printAllWithSalaryBelow (Double salary){
        if(getSize(employees)<=0){
            System.out.println("Error. No employees found!");
            return;
        }
        for(Employee e:employees){
            if(e!=null&&e.getSalary()<salary){
                System.out.println(String.format("\tid:" + e.getId() + " Name: " + e.getName()  + "\tSalary: %.2f $",e.getSalary()));
            }
        }
    }

    //Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
    public void printAllWithSalaryAbove (Double salary){
        if(getSize(employees)<=0){
            System.out.println("Error. No employees found!");
            return;
        }
        for(Employee e:employees){
            if(e!=null&&e.getSalary()>=salary){
                System.out.println(String.format("\tid:" + e.getId() + " Name: " + e.getName()  + "\tSalary: %.2f $",e.getSalary()));
            }
        }
    }


    /**
     *
     * Задания из раздела "Очень сложно"
     *
     */

//    Добавить нового сотрудника (создаем объект, заполняем поля, кладем в массив).
//Нужно найти свободную ячейку в массиве и добавить нового сотрудника в нее. Искать нужно всегда с начала, так как возможно добавление в ячейку удаленных ранее сотрудников.
    public boolean addNewEmployee (String name, Employee.Departments department, Double salary){
        if(employees==null){
            System.out.println("Error!");
            return false;
        }

        Employee employee = new Employee(name, department, salary);

        for(int i=0;i<employees.length;i++){
            if(employees[i]==null){
                employees[i] = employee;
                return true;
            }
        }

        return false;

    }

//    Удалить сотрудника (находим сотрудника по Ф. И. О. и/или id, обнуляем его ячейку в массиве).
    public boolean deleteEmployee (int id){
        //будем искать по ай ди, так как имена могут быть одинаковые а ай ди должен быть уникален
        if(getSize(employees)==0){ // если нет записей то даже не ищем
            return false;
        }


        for(int i=0;i<employees.length;i++){
            if(employees[i].getId()==id){
                employees[i]=null;
                return true;
            }
        }

        return false;

    }

//    Изменить сотрудника (получить сотрудника по Ф. И. О., модернизировать его запись):


//    найдем работника отдельным методом, и сделаем его приватным так как будем использовать только тут
    private Employee findEmployee (String name) {

        if(getSize(employees)<=0){
            return null;
        }

        Employee employee = null;

        for(int i=0;i<employees.length;i++){
            if(employees[i]!=null&&employees[i].getName().equals(name)){
                employee = employees[i];
            }
        }
        
        
        return employee;
    }

    //    Изменить зарплату

    public boolean changeSalary (String name, double newSalary) {
        if(getSize(employees)<=0||newSalary<0){
            return false;
        }

        Employee employee = findEmployee(name);

        if(employee != null){
            employee.setSalary(newSalary);
            return true;
        }

        System.out.println("Employee not found");
        return false;
    }

    // Изменить отдел.

    public boolean changeDepartment (String name, int newDepartment) {
        if(getSize(employees)<=0){
            return false;
        }

        Employee.Departments department = convertIntToEnum(newDepartment);
        if (department == null) {
            return false; // нет такого отдела
        }

        Employee employee = findEmployee(name);

        if(employee != null){
            employee.setDepartment(department);
            return true;
        }

        System.out.println("Employee not found");
        return false;
    }

// Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).

    public void printAllByDepartment (){
        if(getSize(employees)<=0){
            System.out.println("Error. No employees found!");
            return;
        }

        for(int i=1;i<=5;i++){

            Employee[] departmentArray = getDepartment(i);
            if(!printDepartmentEmployees(departmentArray)){
                System.out.println(convertIntToEnum(i) + " has no employees");
            }

        }
    }
    


}
