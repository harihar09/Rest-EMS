package com.ems.service;

import com.ems.dto.EmployeeRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    //save employee
    EmployeeRequestDTO saveEmp(EmployeeRequestDTO employee);

    //get employee with id
    EmployeeRequestDTO getEmpById(Long id);

    //get all employee
    List<EmployeeRequestDTO> getAllEmp();

    //remove emp
    void removeEmp(Long id);

    //update employee details
    EmployeeRequestDTO updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO);

}
