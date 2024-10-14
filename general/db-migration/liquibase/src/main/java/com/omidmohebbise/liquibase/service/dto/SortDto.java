package com.omidmohebbise.liquibase.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortDto {
    private String field;
    private String direction;
}
