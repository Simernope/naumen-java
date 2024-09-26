package naumen;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    // Конструктор
    public Employee(String fullName, Integer age, String department, Double salary) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // Геттеры и сеттеры
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{fullName='" + fullName + "', age=" + age + ", department='" + department + "', salary=" + salary + "}";
    }
}

public class task3 {
    public static void main(String[] args) {
        // Предзаполненный список сотрудников
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivan Petrov", 35, "IT", 1500.0));
        employees.add(new Employee("Anna Ivanova", 28, "HR", 1200.0));
        employees.add(new Employee("Petr Sidorov", 45, "IT", 1800.0));
        employees.add(new Employee("Maria Smirnova", 32, "HR", 1300.0));
        employees.add(new Employee("Olga Kuznetsova", 40, "IT", 1700.0));

        // Указанный департамент
        String department = "IT";

        // Поиск средней зарплаты сотрудников отдела с использованием Stream API
        OptionalDouble averageSalary = employees.stream()
                .filter(employee -> employee.getDepartment().equals(department)) // Фильтруем по департаменту
                .mapToDouble(Employee::getSalary) // Получаем зарплаты
                .average(); // Вычисляем среднее

        // Выводим результат
        if (averageSalary.isPresent()) {
            System.out.printf("Средняя зарплата сотрудников отдела %s: %.2f\n", department, averageSalary.getAsDouble());
        } else {
            System.out.println("Нет сотрудников в отделе " + department);
        }
    }
}
