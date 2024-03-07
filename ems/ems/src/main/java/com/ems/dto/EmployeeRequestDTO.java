package com.ems.dto;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
public class EmployeeRequestDTO {
    private Long id;
    @Size(min = 2,max = 5,message = "name should be at least 2 char")
    private String name;
    private Date doj;
    private Long salary;
}
