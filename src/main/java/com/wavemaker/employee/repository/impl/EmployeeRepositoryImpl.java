package com.wavemaker.employee.repository.impl;

import com.wavemaker.employee.controller.Main;
import com.wavemaker.employee.pojo.Employee;
import com.wavemaker.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Map<Integer, Employee> employeeMap = new HashMap<>();

    @Override
    public int addEmployee(Employee employee) {
        logger.info("Adding new employee: {}", employee);
        int empId = -1;
        empId = getMaxEmpId();
        empId += 1;
        employee.setEmpId(empId);
        logger.debug("Generated new employee ID: {}", empId);

        logger.info("Adding employee with ID: {}", employee.getEmpId());

        if (employeeMap.containsKey(employee.getEmpId())) {
            logger.error("Employee with ID {} already exists.", employee.getEmpId());

        }
        employeeMap.put(employee.getEmpId(), employee);
        logger.info("Employee with ID {} added successfully.", employee.getEmpId());
        return empId;
    }

    @Override
    public Employee getEmployeeById(int empId) {
        logger.info("Fetching employee with ID: {}", empId);
        Employee employee = employeeMap.get(empId);
        if (employee == null) {
            logger.error("Employee with ID {} not found.", empId);
        }
        logger.info("Employee found: {}", employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees.");
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public boolean deleteEmployee(int empId) {
        logger.info("Deleting employee with ID: {}", empId);
        if (!employeeMap.containsKey(empId)) {
            logger.error("Employee with ID {} not found.", empId);
            return false;
        }
        employeeMap.remove(empId);
        logger.info("Employee with ID {} deleted successfully.", empId);
        return true;

    }

    @Override
    public boolean updateEmployee(Employee employee) {
        logger.info("Updating employee with ID: {}", employee.getEmpId());
        if (!employeeMap.containsKey(employee.getEmpId())) {
            logger.error("Employee with ID {} not found.", employee.getEmpId());
            return false;
        }
        employeeMap.put(employee.getEmpId(), employee);
        logger.info("Employee with ID {} updated successfully.", employee.getEmpId());
        return true;
    }

    private int getMaxEmpId() {
        int maxEmpId = 0;
        for (int i : employeeMap.keySet()) {
            maxEmpId = Math.max(maxEmpId, i);
        }
        return maxEmpId;
    }
}
