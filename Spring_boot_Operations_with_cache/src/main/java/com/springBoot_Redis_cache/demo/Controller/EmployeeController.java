package com.springBoot_Redis_cache.demo.Controller;

import com.springBoot_Redis_cache.demo.Entity.Employee;
import com.springBoot_Redis_cache.demo.Service.EmployeeService;
import com.springBoot_Redis_cache.demo.Service.ServiceImpl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeServiceImpl employeeService;


    @PostMapping("/save")
    public ResponseEntity<String> saveEmployeeData(@RequestBody Employee employee)
    {
        String str= employeeService.saveEmployeeData(employee);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Integer employeeId)
    {
        Employee employee= employeeService.getEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);

    }

    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
       List<Employee> list=  employeeService.getAllEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/get1")
    public ResponseEntity<List<Employee>> getAllEmployeeByName(@RequestParam("employeeName")  String employeeFirstName)
    {
        List<Employee> list=employeeService.getAllEmployeeByName(employeeFirstName);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/get2/{employeeEmail}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable("employeeEmail") String employeeEmail)
    {
        Employee employee= employeeService.getEmployeeByEmail(employeeEmail);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateEmployeeById(@RequestParam("employeeId") Integer employeeId,@RequestBody Employee employee)
    {
        employeeService.updateEmployeeById(employeeId, employee);
        return ResponseEntity.status(HttpStatus.OK).body("Data updated Successfully");
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("employeeId") Integer employeeId)
    {
        String str= employeeService.deleteEmployeeById(employeeId);
       return ResponseEntity.status(HttpStatus.OK).body(str);
    }

}
