package dev.robgleason.departmentservice.service;

import dev.robgleason.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
}
