package dev.robgleason.employeeservice.service;

import dev.robgleason.employeeservice.dto.APIResponseDto;
import dev.robgleason.employeeservice.dto.DepartmentDto;
import dev.robgleason.employeeservice.dto.EmployeeDto;
import dev.robgleason.employeeservice.entity.Employee;
import dev.robgleason.employeeservice.exceptions.ResourceNotFoundException;
import dev.robgleason.employeeservice.mapper.AutoEmployeeMapper;
import dev.robgleason.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;


    private WebClient webClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
