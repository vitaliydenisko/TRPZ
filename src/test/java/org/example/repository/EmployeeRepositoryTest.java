package org.example.repository;

import org.example.entity.EmployeeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = EmployeeRepository.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void findByDepartment_ReturnsEmployees_WhenEmployeesExistInDepartment() {
        String department = "HR";
        List<EmployeeEntity> employees = List.of(new EmployeeEntity(), new EmployeeEntity());
        Mockito.when(employeeRepository.findByDepartment(department)).thenReturn(employees);

        List<EmployeeEntity> result = employeeRepository.findByDepartment(department);

        Assertions.assertEquals(employees, result);
    }

    @Test
    void findByDepartment_ReturnsEmptyList_WhenNoEmployeesExistInDepartment() {
        String department = "HR";
        Mockito.when(employeeRepository.findByDepartment(department)).thenReturn(Collections.emptyList());

        List<EmployeeEntity> result = employeeRepository.findByDepartment(department);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void findByName_ReturnsEmployees_WhenEmployeesExistWithName() {
        String name = "John";
        List<EmployeeEntity> employees = List.of(new EmployeeEntity(), new EmployeeEntity());
        Mockito.when(employeeRepository.findByName(name)).thenReturn(employees);

        List<EmployeeEntity> result = employeeRepository.findByName(name);

        Assertions.assertEquals(employees, result);
    }

    @Test
    void findByName_ReturnsEmptyList_WhenNoEmployeesExistWithName() {
        String name = "John";
        Mockito.when(employeeRepository.findByName(name)).thenReturn(Collections.emptyList());

        List<EmployeeEntity> result = employeeRepository.findByName(name);

        Assertions.assertTrue(result.isEmpty());
    }
}