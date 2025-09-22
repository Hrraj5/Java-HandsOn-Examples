package org.example.javaexamples;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Employee{
    private String name;
    private int salary;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Employee(String name, int salary, String department, int age) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String department;
}
public class StreamsAPI {
    public static void main(String[] args) {
        // Given a list of integers, return a list containing the squares of even numbers.
        List<Integer> list = List.of(1,2,4,6,7,8);
        List<Integer> list1 = list.stream().filter(num-> num%2==0).map(num-> num*num).toList();
        System.out.println(list1);


        // From a list of strings, return unique elements sorted by length and then alphabetically.
        List<String> words = List.of("pomegranate","mango","apple","litchi","guava","pear");
        List<String>sortedWords = words.stream().sorted(Comparator.comparing(String::length))
                .sorted(String::compareTo)
                .toList();
        System.out.println(sortedWords);


        //You have a List<List<String>>. Flatten it into a single list of strings.
        List<String> listOfName  = List.of("Ramesh","Sri","Ram");
        List<String> listOfLastName = List.of("Raj","Singh","sir");
        List<List<String>> name  = List.of(listOfName,listOfLastName);
        List<String> names  = name.stream().flatMap(n-> n.stream()).toList();
        System.out.println(names);

        Optional<String> word  = words.stream().filter(w-> w.startsWith("a")).findFirst();
        System.out.println(word.get());

        // Given a list of employees, group them by department and count employees in each department.
        List<Employee> employeeList = List.of(new Employee("Hrithik",5000,"Engineering",25),
                new Employee("Ramesh",6000,"Engineering",45),
                new Employee("Sam",7000,"HR",45),
                        new Employee("Suresh",8000,"HR",45));

        Map<String,Long> empCount = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(empCount);

        // Partition numbers into prime and non-prime using streams.
        List<Integer> numbers = List.of(2,3,4,5,6,17,54);
        Map<Boolean,List<Integer>> primeResult = numbers.stream().collect(Collectors.partitioningBy(num-> checkPrimeNumbers(num)));
        System.out.println(primeResult);
        // Use streams to safely get the maximum salary from a list of employees, returning an Optional<Integer>.
        Optional<Integer> salary = employeeList.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .map(Employee::getSalary).findFirst();
        System.out.println(salary.get());

        List<Optional<String>> values = List.of(Optional.of("Tea"),Optional.of("Coffee"),Optional.empty());
        System.out.println(values);
        List<String> nonEmptyValues = values.stream().flatMap(Optional::stream).toList();
        System.out.println(nonEmptyValues);

        Optional<Integer> sum  = numbers.stream().reduce((a, b)-> a+b);
        int factorial = IntStream.rangeClosed(1,5).reduce(1,(a,b)->a*b);
        System.out.println(factorial);

        Map<String,List<Employee>> salarymap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Map<String,List<Employee>> sortedSalaryMap = new HashMap<>();
        for(Map.Entry<String,List<Employee>> entry : salarymap.entrySet()){
            sortedSalaryMap.put(entry.getKey(),entry.getValue().stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).limit(3).toList());
        }

        for(Map.Entry<String,List<Employee>> entry : sortedSalaryMap.entrySet()){
            System.out.println("Key : " +  entry.getKey());
            entry.getValue().forEach(employee -> System.out.println("Value : " + employee.getSalary()));
        }

        // OR
        Map<String,List<Employee>> sE= employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,   // group by dept
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list2 -> list2.stream()
                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                        .limit(3)
                                        .collect(Collectors.toList())
                        )
                ));

        String para = " Hi hello ram shyam hi hello whatsup hi hello";
        Map<String,Long> maxFrequency= Arrays.stream(para.split(" ")).filter(word1-> !word1.isEmpty()).collect(Collectors.groupingBy(word1-> word1, Collectors.counting()));
        Map<String,Long> top5Words = new HashMap<>();
        long maxF = 0l;
        for(Map.Entry<String,Long> entry: maxFrequency.entrySet()){
            if(entry.getValue()>maxF){
                maxF = entry.getValue();
                top5Words.put(entry.getKey(), entry.getValue());
            }
        }
        System.out.println(top5Words);
        Map<String,Long> maxFrequency1= maxFrequency.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
        //System.out.println(maxFrequency1);

    }
    public static boolean checkPrimeNumbers(int num){
        if(num == 2) return true;
        for(int i=2;i<=num;i++){
            if(num%2==0) return false;
        }
        return true;
    }
}
