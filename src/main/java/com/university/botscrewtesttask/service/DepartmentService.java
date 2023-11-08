package com.university.botscrewtesttask.service;


public interface DepartmentService {
    String getHeadOfDepartment(String departmentName);

    Long getEmployeeCountByDepartment(String departmentName);

    String getSumOfDifferentDegreesWithinDepartment(String departmentName);
}
