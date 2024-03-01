package com.springBoot_Redis_cache.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable {

    private static final Long serialVersionUID= 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabets allowed")
    @Column(name = "employee_first_name")
    private String employeeFirstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only Alphabets allowed")
    @Column(name = "employee_last_name")
    @NotNull
    private String employeeLastName;

    @Email(regexp = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b", message = "Email must ends with .gmail.com")
    @NotNull
    @Column(name = "employee_email")
    private String employeeEmail;

    @Size(min = 10, max = 10, message = "Phone number must be of 10 digits")
    @Column(name = "employee_phone_no")
    private String employeePhoneNo;

    @Column(name = "employee_salary")
    @NotNull
    @DecimalMin(value = "10", message = "Minimum salary is 10")
    @DecimalMax(value = "100000", message = "Maximum salary is 100000")
    private Double employeeSalary;

    @NotNull
    @Column(name = "employee_address")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabets allowed")
    private String employeeAddress;



}
