package com.ems.service;

import com.ems.dto.EmployeeRequestDTO;
import com.ems.repository.EmployeeRepo;
import com.ems.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.ems.mapper.EmpMapper.mapToDTO;
import static com.ems.mapper.EmpMapper.mapToEntity;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    Logger log = Logger.getLogger("EmployeeServiceImpl.class");
    //here we need EmployeeRepo object to save obj in db
    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public EmployeeRequestDTO saveEmp(EmployeeRequestDTO employeeDTO) {
        //map to entity
        EmployeeEntity employeeEntity = mapToEntity(employeeDTO);
        //call the predefined save method
        EmployeeEntity saveEmployee = employeeRepo.save(employeeEntity);
        //map to dto
        return mapToDTO(saveEmployee);
    }

    @Override
    public EmployeeRequestDTO getEmpById(Long id) {
        Optional.ofNullable(id).orElseThrow(() -> new RuntimeException("id is null"));
        //get an employee with given id, if not found then throw custom exception
        Optional<EmployeeEntity> emp = employeeRepo.findById(id);
        EmployeeEntity employeeEntity = emp.get();
        return mapToDTO(employeeEntity);
    }

    @Override
    public List<EmployeeRequestDTO> getAllEmp() {
        //get all emp entities and map it to dto
        return employeeRepo.findAll().stream()
                .map(employeeEntity -> mapToDTO(employeeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void removeEmp(Long id) {
        Optional.ofNullable(id).orElseThrow(() -> new RuntimeException("id is null"));
        employeeRepo.deleteById(id);
    }

    @Override
    public EmployeeRequestDTO updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Optional.ofNullable(id).orElseThrow(() -> new RuntimeException("id is null"));
        //get and update the employee details
        Optional<EmployeeEntity> employee = employeeRepo.findById(id);
        //update this employee with new details and save it
        if (employee.isPresent()) {
            EmployeeEntity employeeEntity = employee.get();
            if (employeeRequestDTO.getName() != null)
                employeeEntity.setName(employeeRequestDTO.getName());
            if (employeeRequestDTO.getDoj() != null)
                employeeEntity.setDoj(employeeRequestDTO.getDoj());
            if (employeeRequestDTO.getSalary() != null)
                employeeEntity.setSalary(employeeRequestDTO.getSalary());
            EmployeeEntity updatedEmployeeDetails = employeeRepo.save(employeeEntity);
            //convert to dto and return
            return mapToDTO(updatedEmployeeDetails);
        }
        return null;
    }
}
