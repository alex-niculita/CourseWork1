package pro.sky.coursework1;

public class Employee {
    private static int counter;
    private int id;
    private String name;
    private Departments department;
    private double salary;

    enum Departments{
        IT,
        FINANCE,
        HR,
        SALES,
        MARKETING
    }

    public Employee(String name, Departments department, double salary) {
        counter++;
        this.id = counter;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("id:" + id + " Name: " + name + " (" + department + ")\tSalary: %.2f $",salary);
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Departments getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
