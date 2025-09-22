package org.example.javaexamples;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Employee1 {
    private String name;
    private String department;
    private int age;
    private double salary;
    private LocalDate joinDate;

    public Employee1(String name, String department, int age, double salary, LocalDate joinDate) {
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public LocalDate getJoinDate() { return joinDate; }

    @Override
    public String toString() {
        return String.format("%s(%s, %d, %.0f)", name, department, age, salary);
    }
}

public class CollectorsGuide {

    public static void main(String[] args) {
        List<Employee1> employees = createEmployees();
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("=== JAVA COLLECTORS COMPREHENSIVE GUIDE ===\n");

        // 1. BASIC COLLECTION COLLECTORS
        basicCollectionCollectors(employees, words, numbers);

        // 2. GROUPING COLLECTORS
        groupingCollectors(employees);

        // 3. PARTITIONING COLLECTORS
        partitioningCollectors(employees, numbers);

        // 4. MATHEMATICAL COLLECTORS
        mathematicalCollectors(employees, numbers);

        // 5. STRING COLLECTORS
        stringCollectors(employees, words);

        // 6. MAPPING COLLECTORS
        mappingCollectors(employees);

        // 7. FILTERING COLLECTORS
        filteringCollectors(employees);

        // 8. REDUCING COLLECTORS
        reducingCollectors(employees, numbers);

        // 9. OPTIONAL COLLECTORS
        optionalCollectors(employees, numbers);

        // 10. ADVANCED COLLECTORS
        advancedCollectors(employees);

        // 11. CONCURRENT COLLECTORS
        concurrentCollectors(employees);

        // 12. CUSTOM COLLECTORS
        customCollectors(employees, numbers);
    }

    // 1. BASIC COLLECTION COLLECTORS
    public static void basicCollectionCollectors(List<Employee1> employees, List<String> words, List<Integer> numbers) {
        System.out.println("=== 1. BASIC COLLECTION COLLECTORS ===");

        // toList() - Most common
        List<String> names = employees.stream()
                .map(Employee1::getName)
                .collect(Collectors.toList());
        System.out.println("toList(): " + names);

        // toSet() - Remove duplicates
        Set<String> departments = employees.stream()
                .map(Employee1::getDepartment)
                .collect(Collectors.toSet());
        System.out.println("toSet(): " + departments);

        // toCollection() - Custom collection type
        LinkedList<String> linkedNames = employees.stream()
                .map(Employee1::getName)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("toCollection(LinkedList): " + linkedNames.getClass().getSimpleName());

        // toMap() - Convert to Map
        Map<String, Double> salaryMap = employees.stream()
                .collect(Collectors.toMap(
                        Employee1::getName,
                        Employee1::getSalary
                ));
        System.out.println("toMap(): " + salaryMap);

        // toMap() with merge function for duplicates
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.toMap(
                        Employee1::getDepartment,
                        Employee1::getSalary,
                        Double::max  // Keep maximum salary if duplicate departments
                ));
        System.out.println("toMap() with merge: " + avgSalaryByDept);

        System.out.println();
    }

    // 2. GROUPING COLLECTORS
    public static void groupingCollectors(List<Employee1> employees) {
        System.out.println("=== 2. GROUPING COLLECTORS ===");

        // groupingBy() - Basic grouping
        Map<String, List<Employee1>> byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee1::getDepartment));
        System.out.println("groupingBy() department:");
        byDepartment.forEach((dept, emps) -> System.out.println("  " + dept + ": " + emps.size() + " employees"));

        // groupingBy() with downstream collector - counting
        Map<String, Long> countByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("groupingBy() with counting(): " + countByDepartment);

        // groupingBy() with summingDouble
        Map<String, Double> totalSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.summingDouble(Employee1::getSalary)
                ));
        System.out.println("groupingBy() with summingDouble(): " + totalSalaryByDept);

        // groupingBy() with averagingDouble
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.averagingDouble(Employee1::getSalary)
                ));
        System.out.println("groupingBy() with averagingDouble(): " + avgSalaryByDept);

        // groupingBy() with custom map type and downstream
        LinkedHashMap<String, Set<String>> namesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        LinkedHashMap::new,  // Custom map type
                        Collectors.mapping(Employee1::getName, Collectors.toSet())
                ));
        System.out.println("groupingBy() with LinkedHashMap: " + namesByDept);

        // Multi-level grouping
        Map<String, Map<String, List<Employee1>>> multiLevel = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.groupingBy(emp -> emp.getAge() < 30 ? "Young" : "Senior")
                ));
        System.out.println("Multi-level grouping:");
        multiLevel.forEach((dept, ageGroups) -> {
            System.out.println("  " + dept + ":");
            ageGroups.forEach((ageGroup, emps) ->
                    System.out.println("    " + ageGroup + ": " + emps.size()));
        });

        System.out.println();
    }

    // 3. PARTITIONING COLLECTORS
    public static void partitioningCollectors(List<Employee1> employees, List<Integer> numbers) {
        System.out.println("=== 3. PARTITIONING COLLECTORS ===");

        // partitioningBy() - Split into two groups
        Map<Boolean, List<Employee1>> seniorPartition = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getAge() >= 30));
        System.out.println("partitioningBy() age >= 30:");
        System.out.println("  Senior (true): " + seniorPartition.get(true).size());
        System.out.println("  Junior (false): " + seniorPartition.get(false).size());

        // partitioningBy() with downstream collector
        Map<Boolean, Double> avgSalaryBySeniority = employees.stream()
                .collect(Collectors.partitioningBy(
                        emp -> emp.getAge() >= 30,
                        Collectors.averagingDouble(Employee1::getSalary)
                ));
        System.out.println("partitioningBy() with averagingDouble(): " + avgSalaryBySeniority);

        // partitioningBy() with numbers
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("partitioningBy() even/odd: " + evenOdd);

        System.out.println();
    }

    // 4. MATHEMATICAL COLLECTORS
    public static void mathematicalCollectors(List<Employee1> employees, List<Integer> numbers) {
        System.out.println("=== 4. MATHEMATICAL COLLECTORS ===");

        // counting()
        long totalEmployees = employees.stream().collect(Collectors.counting());
        System.out.println("counting(): " + totalEmployees);

        // summingInt(), summingLong(), summingDouble()
        double totalSalary = employees.stream()
                .collect(Collectors.summingDouble(Employee1::getSalary));
        System.out.println("summingDouble(): " + totalSalary);

        int totalAge = employees.stream()
                .collect(Collectors.summingInt(Employee1::getAge));
        System.out.println("summingInt(): " + totalAge);

        // averagingInt(), averagingLong(), averagingDouble()
        double avgSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee1::getSalary));
        System.out.println("averagingDouble(): " + avgSalary);

        // summarizingInt(), summarizingLong(), summarizingDouble()
        DoubleSummaryStatistics salaryStats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee1::getSalary));
        System.out.println("summarizingDouble(): " + salaryStats);

        IntSummaryStatistics numberStats = numbers.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("summarizingInt(): " + numberStats);

        System.out.println();
    }

    // 5. STRING COLLECTORS
    public static void stringCollectors(List<Employee1> employees, List<String> words) {
        System.out.println("=== 5. STRING COLLECTORS ===");

        // joining() - Simple join
        String allNames = employees.stream()
                .map(Employee1::getName)
                .collect(Collectors.joining());
        System.out.println("joining(): " + allNames);

        // joining() with delimiter
        String namesWithComma = employees.stream()
                .map(Employee1::getName)
                .collect(Collectors.joining(", "));
        System.out.println("joining() with delimiter: " + namesWithComma);

        // joining() with delimiter, prefix, and suffix
        String formalList = employees.stream()
                .map(Employee1::getName)
                .collect(Collectors.joining(", ", "Employees: [", "]"));
        System.out.println("joining() with prefix/suffix: " + formalList);

        // joining() with transformation
        String departmentList = employees.stream()
                .map(emp -> emp.getDepartment().toUpperCase())
                .distinct()
                .collect(Collectors.joining(" | ", "Departments: ", ""));
        System.out.println("joining() with transformation: " + departmentList);

        System.out.println();
    }

    // 6. MAPPING COLLECTORS
    public static void mappingCollectors(List<Employee1> employees) {
        System.out.println("=== 6. MAPPING COLLECTORS ===");

        // mapping() - Transform elements before collecting
        Map<String, Set<String>> namesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.mapping(Employee1::getName, Collectors.toSet())
                ));
        System.out.println("mapping() with groupingBy(): " + namesByDept);

        // mapping() with different transformations
        Map<String, List<Integer>> agesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.mapping(Employee1::getAge, Collectors.toList())
                ));
        System.out.println("mapping() ages by department: " + agesByDept);

        // mapping() with joining
        Map<String, String> joinedNamesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.mapping(Employee1::getName, Collectors.joining(", "))
                ));
        System.out.println("mapping() with joining(): " + joinedNamesByDept);

        System.out.println();
    }

    // 7. FILTERING COLLECTORS (Java 9+)
    public static void filteringCollectors(List<Employee1> employees) {
        System.out.println("=== 7. FILTERING COLLECTORS ===");

        // filtering() - Filter elements before downstream collector
        Map<String, List<Employee1>> seniorsByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.filtering(emp -> emp.getAge() >= 30, Collectors.toList())
                ));
        System.out.println("filtering() seniors by department:");
        seniorsByDept.forEach((dept, emps) -> System.out.println("  " + dept + ": " + emps.size()));

        // filtering() with counting
        Map<String, Long> seniorCountByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.filtering(emp -> emp.getAge() >= 30, Collectors.counting())
                ));
        System.out.println("filtering() with counting(): " + seniorCountByDept);

        // filtering() with summingDouble
        Map<String, Double> highSalaryTotalByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::getDepartment,
                        Collectors.filtering(emp -> emp.getSalary() > 70000,
                                Collectors.summingDouble(Employee1::getSalary))
                ));
        System.out.println("filtering() high salaries by department: " + highSalaryTotalByDept);

        System.out.println();
    }

    // 8. REDUCING COLLECTORS
    public static void reducingCollectors(List<Employee1> employees, List<Integer> numbers) {
        System.out.println("=== 8. REDUCING COLLECTORS ===");

        // reducing() - Custom reduction
        Optional<Employee1> highestPaid = employees.stream()
                .collect(Collectors.reducing(
                        (emp1, emp2) -> emp1.getSalary() > emp2.getSalary() ? emp1 : emp2
                ));
        System.out.println("reducing() highest paid: " + highestPaid.orElse(null));

        // reducing() with identity and mapper
        double totalSalary = employees.stream()
                .collect(Collectors.reducing(
                        0.0,                    // Identity
                        Employee1::getSalary,    // Mapper
                        Double::sum            // Reducer
                ));
        System.out.println("reducing() total salary: " + totalSalary);

        // reducing() with BigDecimal for precision
        BigDecimal totalSalaryPrecise = employees.stream()
                .collect(Collectors.reducing(
                        BigDecimal.ZERO,
                        emp -> BigDecimal.valueOf(emp.getSalary()),
                        BigDecimal::add
                ));
        System.out.println("reducing() with BigDecimal: " + totalSalaryPrecise);

        // reducing() for product calculation
        int product = numbers.stream()
                .collect(Collectors.reducing(1, Integer::intValue, (a, b) -> a * b));
        System.out.println("reducing() product: " + product);

        System.out.println();
    }

    // 9. OPTIONAL COLLECTORS
    public static void optionalCollectors(List<Employee1> employees, List<Integer> numbers) {
        System.out.println("=== 9. COLLECTORS RETURNING OPTIONAL ===");

        // maxBy() and minBy()
        Optional<Employee1> oldestEmployee = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Employee1::getAge)));
        System.out.println("maxBy() oldest: " + oldestEmployee.orElse(null));

        Optional<Employee1> youngestEmployee = employees.stream()
                .collect(Collectors.minBy(Comparator.comparing(Employee1::getAge)));
        System.out.println("minBy() youngest: " + youngestEmployee.orElse(null));

        // maxBy() with custom comparator
        Optional<Employee1> highestPaid = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Employee1::getSalary)));
        System.out.println("maxBy() highest paid: " + highestPaid.orElse(null));

        System.out.println();
    }

    // 10. ADVANCED COLLECTORS
    public static void advancedCollectors(List<Employee1> employees) {
        System.out.println("=== 10. ADVANCED COLLECTORS ===");

        // collectingAndThen() - Transform result after collecting
        String summary = employees.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.counting(),
                        count -> "Total employees: " + count
                ));
        System.out.println("collectingAndThen(): " + summary);

        // collectingAndThen() with immutable result
        List<String> immutableNames = employees.stream()
                .map(Employee1::getName)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));
        System.out.println("collectingAndThen() immutable: " + immutableNames.getClass().getSimpleName());

        // teeing() - Java 12+ (combine two collectors)
        // Note: This might not compile on older Java versions
        try {
            var result = employees.stream()
                    .collect(Collectors.teeing(
                            Collectors.averagingDouble(Employee1::getSalary),
                            Collectors.counting(),
                            (avgSalary, count) -> String.format("Avg Salary: %.2f, Count: %d", avgSalary, count)
                    ));
            System.out.println("teeing(): " + result);
        } catch (Exception e) {
            System.out.println("teeing(): Not available in this Java version");
        }

        System.out.println();
    }

    // 11. CONCURRENT COLLECTORS
    public static void concurrentCollectors(List<Employee1> employees) {
        System.out.println("=== 11. CONCURRENT COLLECTORS ===");

        // toConcurrentMap() - Thread-safe map
        ConcurrentMap<String, Double> concurrentSalaryMap = employees.parallelStream()
                .collect(Collectors.toConcurrentMap(
                        Employee1::getName,
                        Employee1::getSalary
                ));
        System.out.println("toConcurrentMap(): " + concurrentSalaryMap.getClass().getSimpleName());

        // groupingByConcurrent() - Thread-safe grouping
        ConcurrentMap<String, List<Employee1>> concurrentGrouping = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(Employee1::getDepartment));
        System.out.println("groupingByConcurrent(): " + concurrentGrouping.getClass().getSimpleName());

        System.out.println();
    }

    // 12. CUSTOM COLLECTORS
    public static void customCollectors(List<Employee1> employees, List<Integer> numbers) {
        System.out.println("=== 12. CUSTOM COLLECTORS ===");

        // Custom collector for statistics
        Collector<Employee1, ?, Map<String, Object>> salaryStatsCollector =
                Collector.of(
                        () -> new double[3],  // supplier: [sum, count, max]
                        (acc, emp) -> {       // accumulator
                            acc[0] += emp.getSalary();
                            acc[1]++;
                            acc[2] = Math.max(acc[2], emp.getSalary());
                        },
                        (acc1, acc2) -> {     // combiner
                            acc1[0] += acc2[0];
                            acc1[1] += acc2[1];
                            acc1[2] = Math.max(acc1[2], acc2[2]);
                            return acc1;
                        },
                        acc -> {              // finisher
                            Map<String, Object> result = new HashMap<>();
                            result.put("total", acc[0]);
                            result.put("count", (long)acc[1]);
                            result.put("average", acc[1] > 0 ? acc[0] / acc[1] : 0);
                            result.put("max", acc[2]);
                            return result;
                        }
                );

        Map<String, Object> salaryStats = employees.stream()
                .collect(salaryStatsCollector);
        System.out.println("Custom salary stats collector: " + salaryStats);

        // Custom collector for unique count
        Collector<String, ?, Integer> uniqueCountCollector =
                Collector.of(
                        HashSet::new,
                        HashSet::add,
                        (set1, set2) -> { set1.addAll(set2); return set1; },
                        HashSet::size
                );

        Integer uniqueDepartmentCount = employees.stream()
                .map(Employee1::getDepartment)
                .collect(uniqueCountCollector);
        System.out.println("Custom unique count collector: " + uniqueDepartmentCount);

        System.out.println();
    }

    // Helper method to create sample employees
    public static List<Employee1> createEmployees() {
        return Arrays.asList(
                new Employee1("Alice", "Engineering", 28, 75000, LocalDate.of(2020, 1, 15)),
                new Employee1("Bob", "Engineering", 32, 85000, LocalDate.of(2019, 3, 22)),
                new Employee1("Charlie", "Marketing", 29, 65000, LocalDate.of(2021, 6, 10)),
                new Employee1("Diana", "Marketing", 35, 70000, LocalDate.of(2018, 9, 5)),
                new Employee1("Eve", "HR", 26, 55000, LocalDate.of(2022, 2, 14)),
                new Employee1("Frank", "Engineering", 31, 90000, LocalDate.of(2017, 11, 30)),
                new Employee1("Grace", "Finance", 33, 80000, LocalDate.of(2019, 7, 18)),
                new Employee1("Henry", "Finance", 27, 60000, LocalDate.of(2021, 12, 3))
        );
    }
}

/*
=== COLLECTORS SUMMARY BY CATEGORY ===

1. BASIC COLLECTIONS:
   - toList(), toSet(), toCollection()
   - toMap(), toConcurrentMap()

2. GROUPING:
   - groupingBy(), groupingByConcurrent()
   - Multi-level grouping supported

3. PARTITIONING:
   - partitioningBy() - splits into exactly 2 groups (true/false)

4. MATHEMATICAL:
   - counting()
   - summingInt(), summingLong(), summingDouble()
   - averagingInt(), averagingLong(), averagingDouble()
   - summarizingInt(), summarizingLong(), summarizingDouble()

5. STRING OPERATIONS:
   - joining(), joining(delimiter), joining(delimiter, prefix, suffix)

6. TRANSFORMING:
   - mapping() - transform elements before downstream collector
   - filtering() - filter elements before downstream collector (Java 9+)

7. REDUCING:
   - reducing() - custom reduction operations
   - maxBy(), minBy() - return Optional

8. ADVANCED:
   - collectingAndThen() - transform final result
   - teeing() - combine two collectors (Java 12+)

9. CONCURRENT:
   - All concurrent versions for parallel streams
   - toConcurrentMap(), groupingByConcurrent()

10. CUSTOM:
    - Collector.of() for creating custom collectors
    - Define supplier, accumulator, combiner, finisher

=== PERFORMANCE TIPS ===
- Use concurrent collectors only with parallel streams
- Filter/map early in the pipeline
- Use primitive collectors (summingInt vs summingDouble) when appropriate
- Consider memory usage with large groupings
- Custom collectors for specialized needs
*/