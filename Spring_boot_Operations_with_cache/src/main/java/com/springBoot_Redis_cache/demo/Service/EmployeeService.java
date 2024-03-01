package com.springBoot_Redis_cache.demo.Service;

import com.springBoot_Redis_cache.demo.Entity.Employee;
import com.springBoot_Redis_cache.demo.Repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface EmployeeService {

    public String saveEmployeeData(Employee employee);
    public Employee getEmployeeById(Integer employeeId);
    public List<Employee> getAllEmployee();
    public List<Employee> getAllEmployeeByName(String employeeFirstName);
    public Employee getEmployeeByEmail(String employeeEmail);
    public Employee updateEmployeeById(Integer employeeId,Employee employee);
    public String deleteEmployeeById(Integer employeeId);


}
