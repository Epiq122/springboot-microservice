package dev.robgleason.employeeservice.service;

import dev.robgleason.employeeservice.dto.APIResponseDto;
import dev.robgleason.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
