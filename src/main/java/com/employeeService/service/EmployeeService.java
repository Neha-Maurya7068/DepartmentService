package com.employeeService.service;

import com.employeeService.dto.APIResponseDto;
import com.employeeService.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmpoyee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
