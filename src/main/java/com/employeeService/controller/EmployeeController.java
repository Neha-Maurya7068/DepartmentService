package com.employeeService.controller;

import com.employeeService.dto.APIResponseDto;
import com.employeeService.dto.EmployeeDto;
import com.employeeService.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {
private EmployeeService employeeService;
@PostMapping
private ResponseEntity<EmployeeDto>savedEmployee(@RequestBody EmployeeDto employeeDto){
    EmployeeDto savedEmployee=employeeService.saveEmpoyee(employeeDto);
    return  new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
}
    @GetMapping("/{id}")
public ResponseEntity<APIResponseDto>getEmployee(@PathVariable("id") Long employeeId){
    APIResponseDto apiResponseDto=employeeService.getEmployeeById(employeeId);
    return  new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
}
}
