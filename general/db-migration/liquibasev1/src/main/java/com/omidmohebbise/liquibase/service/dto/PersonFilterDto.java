package com.omidmohebbise.liquibase.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonFilterDto {
    private List<SortDto> sorts;
    private String firstname;
    private String lastname;
    private Integer age;


}
