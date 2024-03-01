package com.springBoot_Redis_cache.demo.Repository;

import com.springBoot_Redis_cache.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    public Employee findByEmployeeEmail(String employeeEmail);

    @Query(value = "select * from employee where employee_first_name=:name" ,nativeQuery = true)
    public List<Employee> getByEmployeeName(@Param("name") String employeeName);

}
