package org.example.service;

import org.example.dto.EmployeeDto;
import org.example.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity getEmployeeById(Integer id);
    List<EmployeeEntity> getEmployeeByDepartment(String department);
    List<EmployeeEntity> getEmployeeByName(String name);

    EmployeeEntity createEmployee(EmployeeEntity employeeDto);

    EmployeeEntity updateEmployeePositionById(Integer id, String position);
    EmployeeEntity updateEmployeeDepartmentById(Integer id, String department);
}
