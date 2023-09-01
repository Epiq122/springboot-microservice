package dev.robgleason.employeeservice.service;

import dev.robgleason.employeeservice.dto.EmployeeDto;
import dev.robgleason.employeeservice.entity.Employee;
import dev.robgleason.employeeservice.mapper.AutoEmployeeMapper;
import dev.robgleason.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.get();
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(optionalEmployee.get());

    }
}
