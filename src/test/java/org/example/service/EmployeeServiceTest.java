package org.example.service;

import org.example.entity.EmployeeEntity;
import org.example.exception.NotFoundException;
import org.example.repository.EmployeeRepository;
import org.example.service.implementation.EmployeeServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImplementation employeeService;

    @Test
    void getEmployeeById_ReturnsEmployee_WhenEmployeeExists() {
        Integer id = 1;
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        EmployeeEntity result = employeeService.getEmployeeById(id);

        Assertions.assertEquals(employee, result);
    }

    @Test
    void getEmployeeById_ThrowsNotFoundException_WhenEmployeeDoesNotExist() {
        Integer id = 1;
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> employeeService.getEmployeeById(id));
    }

    @Test
    void getEmployeeByDepartment_ReturnsEmployees_WhenEmployeesExistInDepartment() {
        String department = "HR";
        List<EmployeeEntity> employees = List.of(new EmployeeEntity(), new EmployeeEntity());
        Mockito.when(employeeRepository.findByDepartment(department)).thenReturn(employees);

        List<EmployeeEntity> result = employeeService.getEmployeeByDepartment(department);

        Assertions.assertEquals(employees, result);
    }

    @Test
    void getEmployeeByDepartment_ReturnsEmptyList_WhenNoEmployeesExistInDepartment() {
        String department = "HR";
        Mockito.when(employeeRepository.findByDepartment(department)).thenReturn(Collections.emptyList());

        List<EmployeeEntity> result = employeeService.getEmployeeByDepartment(department);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void getEmployeeByName_ReturnsEmployees_WhenEmployeesExistWithName() {
        String name = "John";
        List<EmployeeEntity> employees = List.of(new EmployeeEntity(), new EmployeeEntity());
        Mockito.when(employeeRepository.findByName(name)).thenReturn(employees);

        List<EmployeeEntity> result = employeeService.getEmployeeByName(name);

        Assertions.assertEquals(employees, result);
    }

    @Test
    void getEmployeeByName_ReturnsEmptyList_WhenNoEmployeesExistWithName() {
        String name = "John";
        Mockito.when(employeeRepository.findByName(name)).thenReturn(Collections.emptyList());

        List<EmployeeEntity> result = employeeService.getEmployeeByName(name);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void createEmployee_SavesAndReturnsEmployee() {
        EmployeeEntity employee = new EmployeeEntity();
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeEntity result = employeeService.createEmployee(employee);

        Assertions.assertEquals(employee, result);
    }

    @Test
    void updateEmployeePositionById_UpdatesAndReturnsEmployee_WhenEmployeeExists() {
        Integer id = 1;
        String position = "Manager";
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeEntity result = employeeService.updateEmployeePositionById(id, position);

        Assertions.assertEquals(position, result.getPosition());
    }

    @Test
    void updateEmployeeDepartmentById_UpdatesAndReturnsEmployee_WhenEmployeeExists() {
        Integer id = 1;
        String department = "Finance";
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeEntity result = employeeService.updateEmployeeDepartmentById(id, department);

        Assertions.assertEquals(department, result.getDepartment());
    }
}