package com.wavemaker.employee.service;

import com.wavemaker.employee.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    public int addEmployee(Employee employee);
    public  Employee getEmployeeById(int empId);
    public List<Employee> getAllEmployees();
    public boolean deleteEmployee(int empId);
    public boolean updateEmployee(Employee employee);
}
