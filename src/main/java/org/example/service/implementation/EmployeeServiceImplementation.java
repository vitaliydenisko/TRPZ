package org.example.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.entity.EmployeeEntity;
import org.example.exception.NotFoundException;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
    }

    @Override
    public List<EmployeeEntity> getEmployeeByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<EmployeeEntity> getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employeeDto) {
        return employeeRepository.save(employeeDto);
    }

    @Override
    public EmployeeEntity updateEmployeePositionById(Integer id, String position) {
        EmployeeEntity employee = getEmployeeById(id);
        employee.setPosition(position);
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity updateEmployeeDepartmentById(Integer id, String department) {
        EmployeeEntity employee = getEmployeeById(id);
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }
}
