package com.springBoot_Redis_cache.demo.Service.ServiceImpl;


import com.springBoot_Redis_cache.demo.Entity.Employee;
import com.springBoot_Redis_cache.demo.Exception.DataAlreadyPresentException;
import com.springBoot_Redis_cache.demo.Exception.NoDataPresentEXception;
import com.springBoot_Redis_cache.demo.Repository.EmployeeRepository;
import com.springBoot_Redis_cache.demo.Service.EmployeeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @SneakyThrows
    @Override
    public String saveEmployeeData(Employee employee) {
       if(employeeRepository.findByEmployeeEmail(employee.getEmployeeEmail())!=null)
       {
           throw new DataAlreadyPresentException("Particular email already exists");
       }
       else
       {
           employeeRepository.save(employee);
           return "Data saved successfully";
       }


    }
    @SneakyThrows
    @Override
    @Cacheable(value = "employees", key = "#employeeId")
    public Employee getEmployeeById(Integer employeeId) {

          Optional<Employee> data=employeeRepository.findById(employeeId);

          if(data.isEmpty())
          {
              throw new NoDataPresentEXception("This employeeId= "+employeeId+" data is not available in database");
          }
          else
          {
               return data.get();
          }
    }


    @SneakyThrows
    @Override
    public List<Employee> getAllEmployee() {
         List<Employee> list= employeeRepository.findAll();
         if(list.isEmpty())
         {
             throw new NoDataPresentEXception("No data in database i.e database is empty");
         }
         else
         {
             return list;
         }
    }

    @SneakyThrows
    @Override
    public List<Employee> getAllEmployeeByName(String employeeFirstName) {

         List<Employee> list=employeeRepository.getByEmployeeName(employeeFirstName);
         if(list.isEmpty())
         {
             throw new NoDataPresentEXception("This "+employeeFirstName+" name data is not available in database");
         }
         else
         {
             return list;
         }
    }

    @SneakyThrows
    @Override
    public Employee getEmployeeByEmail(String employeeEmail) {
        Employee employee=  employeeRepository.findByEmployeeEmail(employeeEmail);
        if(employee==null)
        {
            throw  new NoDataPresentEXception("Email "+employeeEmail+" not registered in database");
        }
        else
        {
            return employee;
        }
    }

    @SneakyThrows
    @Override
    @CachePut(value = "employees", key = "#employeeId")
    public Employee updateEmployeeById(Integer employeeId, Employee employee) {
          Optional<Employee> emp= employeeRepository.findById(employeeId);
        if (emp.isEmpty())
        {
            throw new NoDataPresentEXception("There is no data available regarding "+employeeId);
        }
        else {
            employee.setEmployeeId(emp.get().getEmployeeId());
            employeeRepository.save(employee);
            return employee;
        }
    }

    @SneakyThrows
    @Override
    @CacheEvict(value = "employees", key = "#employeeId")
    public String deleteEmployeeById(Integer employeeId) {

     Optional<Employee> emp= employeeRepository.findById(employeeId);
     if(emp.isEmpty())
     {
         throw new NoDataPresentEXception("There is no data related to "+employeeId);
     }
     else
     {
         employeeRepository.deleteById(employeeId);
         return "Data deleted successfully";
     }

    }
}
