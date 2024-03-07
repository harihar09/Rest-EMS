package com.ems.mapper;

import com.ems.dto.EmployeeRequestDTO;
import com.ems.entity.EmployeeEntity;

public class EmpMapper {
    //entity to dto
    public static EmployeeRequestDTO mapToDTO(EmployeeEntity employeeEntity) {
        return EmployeeRequestDTO.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .doj(employeeEntity.getDoj())
                .salary(employeeEntity.getSalary())
                .build();
    }
    //dto to entity
    public static EmployeeEntity mapToEntity(EmployeeRequestDTO employeeDTO) {
        return EmployeeEntity.builder()
                .name(employeeDTO.getName())
                .doj(employeeDTO.getDoj())
                .salary(employeeDTO.getSalary())
                .build();
    }
}
