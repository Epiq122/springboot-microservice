package dev.robgleason.employeeservice.service;

import dev.robgleason.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
}
