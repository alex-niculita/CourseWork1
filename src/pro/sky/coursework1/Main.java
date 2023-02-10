package pro.sky.coursework1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        EmployeeBook employeeBook = new EmployeeBook();

        employeeBook.addNewEmployee("Mark Adams", Employee.Departments.FINANCE,1200.05);
        employeeBook.addNewEmployee("Sarah Thompson", Employee.Departments.IT,1505.35);
        employeeBook.addNewEmployee("Tim Johns", Employee.Departments.SALES,133.2);
        employeeBook.addNewEmployee("Muhammed Abbas", Employee.Departments.SALES,100.00);
        employeeBook.addNewEmployee("Carl Marks", Employee.Departments.SALES,2000.00);
        employeeBook.addNewEmployee("Bruce Lee", Employee.Departments.FINANCE,2005.00);
        employeeBook.addNewEmployee("Andrew Lincoln", Employee.Departments.IT,3000.00);
        employeeBook.addNewEmployee("Norman Reedus", Employee.Departments.IT,4000.00);


        System.out.println("a."); //Получить список всех сотрудников со всеми имеющимися по ним данными.
        employeeBook.printAllEmployees();

        System.out.println("b."); //Посчитать сумму затрат на зарплаты в месяц.
        System.out.printf("\tSalary sum of all employees = %.2f\n", employeeBook.sumSalary());

        System.out.println("c."); //Найти сотрудника с минимальной зарплатой.
        System.out.println("\tEmployee with min salary: " + employeeBook.findEmployeeWithMinSalary());

        System.out.println("d."); //Найти сотрудника с максимальной зарплатой.
        System.out.println("\tEmployee with max salary: " + employeeBook.findEmployeeWithMaxSalary());

        System.out.println("e."); //Подсчитать среднее значение зарплат (можно использовать для этого метод из пункта b).
        System.out.printf("\tMedian salary: %.2f\n", employeeBook.calculateMedianSalary());

        System.out.println("f."); //Получить Ф. И. О. всех сотрудников (вывести в консоль).
        employeeBook.printNamesOnly();

//        Повышенная сложность
        System.out.println("****************************Additional Tasks:");
        System.out.println("1."); //Проиндексировать зарплату (вызвать изменение зарплат у всех сотрудников на величину аргумента в %).
        employeeBook.incSalary(5);
        employeeBook.printAllEmployees();

        System.out.println("2."); //Получить в качестве параметра номер отдела (1–5) и найти (всего 6 методов):

        // вот у нас есть отдельно отдел и его теперь можно поставить в методы которые мы уже написали
        Employee[] employeesInDepartment = employeeBook.getDepartment(4);
        employeeBook.printDepartmentEmployees(employeesInDepartment);

        System.out.println("a."); //Найти сотрудника с минимальной зарплатой.
        System.out.println("\tEmployee with min salary: " + employeeBook.findEmployeeWithMinSalaryForDept(employeesInDepartment));

        System.out.println("b."); //Найти сотрудника с максимальной зарплатой.
        System.out.println("\tEmployee with max salary: " + employeeBook.findEmployeeWithMaxSalaryForDept(employeesInDepartment));

        System.out.println("c."); //Сумму затрат на зарплату по отделу.
        System.out.printf("\tSalary sum of all employees = %.2f\n", employeeBook.sumDeptSalary(employeesInDepartment));

        System.out.println("d."); //Среднюю зарплату по отделу (учесть, что количество людей в отделе отличается от employees.length).
        System.out.printf("\tMedian salary: %.2f\n", employeeBook.calculateMedianSalaryForDept(employeesInDepartment));

        System.out.println("e."); //Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра.
        employeeBook.incSalaryForDept(employeesInDepartment,10);

        System.out.println("f."); //Напечатать всех сотрудников отдела (все данные, кроме отдела).
        employeeBook.printDepartmentEmployees(employeesInDepartment);

        System.out.println("3."); //Получить в качестве параметра число и найти:

        System.out.println("a."); //Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
        employeeBook.printAllWithSalaryBelow(1000.00);

        System.out.println("b."); //Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
        employeeBook.printAllWithSalaryAbove(2000.00);

        /*
         *
         * Задания из раздела "Очень сложно"
         *
         */

        // попробуем добавить более 10 работников для теста
        employeeBook.addNewEmployee("Norman1 Reedus1", Employee.Departments.IT,4500.00); // девятый
        // десятый
        if(employeeBook.addNewEmployee("Norman2 Reedus2", Employee.Departments.IT,4500.00)){
            System.out.println("added");
        } else {
            System.out.println("failed");
        }
        // одиннадцатый
        if(employeeBook.addNewEmployee("Norman3 Reedus3", Employee.Departments.IT,4500.00)){
            System.out.println("added successfully");
        } else {
            System.out.println("add failed");
        }

        // удаляем не существующего сотрудника для теста
        if(employeeBook.deleteEmployee(15)){
            System.out.println("deleted successfully");
        } else {
            System.out.println("delete failed");
        }

        // удаляем существующего сотрудника для теста
        if(employeeBook.deleteEmployee(5)){
            System.out.println("deleted successfully");
        } else {
            System.out.println("delete failed");
        }

        employeeBook.printAllEmployees(); // посмотим действительно ли удалили ай ди 5

        //Изменить сотрудника (получить сотрудника по Ф. И. О., модернизировать его запись):
        //Изменить зарплату.

        //попробуем не правильную зарплату
        if(employeeBook.changeSalary("Sarah Thompson", -50)){
            System.out.println("change salary successfully");
        } else {
            System.out.println("change salary failed");
        }

        //попробуем изменить зарплату у не существующего сотрудника
        if(employeeBook.changeSalary("Anna", 50)){
            System.out.println("change salary successfully");
        } else {
            System.out.println("change salary failed");
        }

        //изменяем зарплату у существующего сотрудника
        if(employeeBook.changeSalary("Bruce Lee", 50)){
            System.out.println("changed salary successfully");
        } else {
            System.out.println("change salary failed");
        }

        employeeBook.printAllEmployees(); // посмотим действительно ли изменили зарплату

        // Изменить отдел.

        //попробуем изменить отдел у не существующего сотрудника
        if (employeeBook.changeDepartment("AAAAAAA",3)) {
            System.out.println("changed dept successfully");
        } else {
            System.out.println("change dept failed");
        }

        //попробуем изменить на не существующий отдел
        if (employeeBook.changeDepartment("Andrew Lincoln",6)) {
            System.out.println("changed dept successfully");
        } else {
            System.out.println("change dept failed");
        }

        //попробуем изменить отдел с корректными параметрами
        if (employeeBook.changeDepartment("Andrew Lincoln",3)) {
            System.out.println("changed dept successfully");
        } else {
            System.out.println("change dept failed");
        }
        employeeBook.printAllEmployees(); // посмотим действительно ли изменили отдел

        // Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
        System.out.println(">>>>>>>>>>>>>>>>>>>>Employees by each department:");
        employeeBook.printAllByDepartment();
    }

}