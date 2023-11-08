package com.university.botscrewtesttask.dto;

import com.university.botscrewtesttask.enums.Degree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDegreeCountDto {
    private Degree degree;
    private Long count;
}
