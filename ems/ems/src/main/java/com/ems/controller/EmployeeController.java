package com.ems.controller;

import com.ems.dto.EmployeeRequestDTO;
import com.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees") // root mapping
public class EmployeeController {
    //here we need service class dependency
    @Autowired
    private EmployeeService employeeService;

    //endpoint for saving emp obj
    @PostMapping("/")
    public ResponseEntity<EmployeeRequestDTO> saveEmp(@Valid @RequestBody EmployeeRequestDTO employee) {
        EmployeeRequestDTO employeeRequestDTO = employeeService.saveEmp(employee);
        return new ResponseEntity<>(employeeRequestDTO, HttpStatus.CREATED);
    }

    // get emp by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRequestDTO> getEmpById(@PathVariable Long id) {
        EmployeeRequestDTO empById = employeeService.getEmpById(id);
        return new ResponseEntity<>(empById, HttpStatus.FOUND);
    }

    // get all employees
    @GetMapping("/")
    public ResponseEntity<List<EmployeeRequestDTO>> getAllEmployees() {
        List<EmployeeRequestDTO> allEmp = employeeService.getAllEmp();
        if (allEmp != null) {
            return new ResponseEntity<>(allEmp, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //update employee details
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRequestDTO> updateEmp(@PathVariable Long id, @RequestBody EmployeeRequestDTO employeeDTO) {
        EmployeeRequestDTO employee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //remove emp details from db based on emp id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long id) {
        employeeService.removeEmp(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
