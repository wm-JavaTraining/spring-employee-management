package com.wavemaker.employee.controller;


import com.wavemaker.employee.pojo.Employee;
import com.wavemaker.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationContext context = new ClassPathXmlApplicationContext("configmetadata.xml");


        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Get Employee by empID");
            System.out.println("3. Get All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            logger.info("user enter the choice for  storage{}", choice);

            switch (choice) {
                case 1:
                    Employee employee = getEmployeeDetails(scanner, context);
                    int empId = employeeService.addEmployee(employee);
                    System.out.println(empId);
                    break;
                case 2:
                    System.out.println("enter empId to get employee details to that empId");
                    empId = scanner.nextInt();
                    employee = employeeService.getEmployeeById(empId);
                    System.out.println(employee);
                    break;
                case 3:
                    List<Employee> listOfEmployees = employeeService.getAllEmployees();
                    for (Employee emp : listOfEmployees) {
                        System.out.println(emp);
                    }
                    break;
                case 4:
                    System.out.println("enter empId to update employee");
                    empId = scanner.nextInt();
                    employee = employeeService.getEmployeeById(empId);
                    if (employee == null) {
                        System.out.println("employee is not present");
                       break;
                    }
                    employee = getEmployeeDetails(scanner, context);
                    if (employee == null) {
                        System.out.println("details are not updated");
                        break;

                    }
                    employee.setEmpId(empId);
                    boolean isUPdated = employeeService.updateEmployee(employee);
                    if (isUPdated) {
                        System.out.println("employee updated successfully");
                    } else {
                        System.out.println("employee is not updated");
                    }
                    break;
                case 5:
                    System.out.println("enter empId to delete employee");
                    empId = scanner.nextInt();
                    scanner.nextLine();
                    boolean isDeleted = employeeService.deleteEmployee(empId);
                    if (isDeleted) {
                        System.out.println("employee deleted successfully");
                    } else {
                        System.out.println("employee is not deleted");
                    }

                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }


    }

    private static Employee getEmployeeDetails(Scanner scanner, ApplicationContext context) {
        System.out.println("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Employee Gender ");
        String gender = scanner.nextLine();
        System.out.println("Enter Employee contact ");
        String contactNumber = scanner.nextLine();
        Employee employee = (Employee) context.getBean("emp");
        employee.setName(name);
        employee.setAge(age);
        employee.setGender(gender);
        employee.setContact(contactNumber);
        return employee;
    }


}
