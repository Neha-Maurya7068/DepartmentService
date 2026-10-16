package com.employeeService.service.impl;

import com.employeeService.dto.APIResponseDto;
import com.employeeService.dto.DepartmentDto;
import com.employeeService.dto.EmployeeDto;
import com.employeeService.entity.Employee;
import com.employeeService.repository.EmployeeRepository;
import com.employeeService.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

//    private RestTemplate restTemplate;
    private WebClient webClient;
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmpoyee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee saveEmployee = employeeRepository.save(employee);

        return new EmployeeDto(
                saveEmployee.getId(),
                saveEmployee.getFirstName(),
                saveEmployee.getLastName(),
                saveEmployee.getEmail(),
                saveEmployee.getDepartmentCode()
        );
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        // Employee find karo
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + employeeId)
                );

//        // Department Service ko call karo
//        ResponseEntity<DepartmentDto> responseEntity =
//                restTemplate.getForEntity(
//                        "http://localhost:8080/api/departments/"
//                                + employee.getDepartmentCode(),
//                        DepartmentDto.class
//                );
//
//        DepartmentDto departmentDto = responseEntity.getBody();
DepartmentDto departmentDto=webClient.get()
        .uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode()).
        retrieve().bodyToMono(DepartmentDto.class).block();


        // Employee DTO
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        // Final response
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
