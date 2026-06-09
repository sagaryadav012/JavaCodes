package org.example.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectors {
    record Employee(String name, String department, double salary) {
    }

    static void main() {
        var employees = List.of(
                new Employee("Amit", "Engineering", 95000),
                new Employee("Riya", "Engineering", 105000),
                new Employee("Rohan", "Sales", 70000),
                new Employee("Neha", "Sales", 82000),
                new Employee("Kiran", "HR", 60000)
        );

        Map<String, List<String>> namesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name, Collectors.toList())));
        System.out.println("names grouped by department: " + namesByDept);

        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)));
        System.out.println("average salary by department: " + avgSalaryByDept);

        double totalPayroll = employees.stream()
                .mapToDouble(Employee::salary)
                .sum();
        System.out.printf("total payroll: %.2f%n", totalPayroll);

        String allNames = employees.stream()
                .map(Employee::name)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("all employee names: " + allNames);
    }
}
