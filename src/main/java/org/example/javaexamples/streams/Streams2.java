package org.example.javaexamples.streams;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class Employee2 {
    String name, department;
    int age;
    double salary;
    LocalDate joinDate;
    // constructors, getters

    @Override
    public String toString() {
        System.out.println( "Employee2{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", joinDate=" + joinDate +
                '}');
        return "";
    }

    public Employee2(String name, String department, int age, double salary, LocalDate joinDate) {
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
}
public class Streams2 {
    public static void main(String[] args) {
        List<Employee2> employees = Arrays.asList(
                // Engineering Department
                new Employee2("Alice Johnson", "Engineering", 28, 75000.00, LocalDate.of(2020, 3, 15)),
                new Employee2("Bob Chen", "Engineering", 32, 85000.00, LocalDate.of(2019, 1, 22)),
                new Employee2("Charlie Davis", "Engineering", 35, 95000.00, LocalDate.of(2018, 6, 10)),

                // Marketing Department
                new Employee2("Diana Rodriguez", "Marketing", 29, 65000.00, LocalDate.of(2021, 4, 5)),
                new Employee2("Eva Thompson", "Marketing", 31, 70000.00, LocalDate.of(2020, 9, 18)),

                // Finance Department
                new Employee2("Frank Wilson", "Finance", 33, 80000.00, LocalDate.of(2019, 7, 12)),
                new Employee2("Grace Kim", "Finance", 27, 72000.00, LocalDate.of(2021, 11, 8)),

                // HR Department
                new Employee2("Henry Martinez", "HR", 26, 58000.00, LocalDate.of(2022, 2, 14)),
                new Employee2("Ivy Patel", "HR", 30, 62000.00, LocalDate.of(2020, 12, 3)),

                // Sales Department
                new Employee2("Jack Brown", "Sales", 34, 68000.00, LocalDate.of(2019, 5, 20)));

        //1. Find employees with salary > 75000 and age < 35
        List<Employee2> empList1 = employees.stream().filter(emp-> emp.getSalary() > 75000 && emp.getAge() < 35).toList();
        //empList1.forEach((Employee2::toString));

        //2. Get average salary by department
        Map<String,Double> averageSalary = employees.stream().collect(Collectors.groupingBy(Employee2::getDepartment, Collectors.averagingDouble(Employee2::getSalary)));
        //System.out.println(averageSalary);

        // 3. Find the top 3 highest-paid employees
        List<Employee2> top3Empl= employees.stream().sorted(Comparator.comparingDouble(Employee2::getSalary).reversed()).limit(3).toList();
        System.out.println(top3Empl);

        Map<String,List<Employee2>> empByExp = employees.stream().collect(Collectors.groupingBy(emp->{
            long years  = ChronoUnit.YEARS.between(emp.getJoinDate(),LocalDate.now());
            if(years <=2) return "Junior";
            else if(years <5) return "Mid";
            else return "Senior";
        }));
        System.out.println(empByExp);
    }

}
