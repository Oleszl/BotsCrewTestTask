package com.university.botscrewtesttask.service.impl;

import com.university.botscrewtesttask.dto.DepartmentDegreeCountDto;
import com.university.botscrewtesttask.entity.Department;
import com.university.botscrewtesttask.exception.DepartmentNotFoundException;
import com.university.botscrewtesttask.repository.DepartmentRepository;
import com.university.botscrewtesttask.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public String getHeadOfDepartment(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(Department::getHeadOfDepartment)
                .map(head -> String.format("Head of %s is %s %s", departmentName, head.getFirstName(), head.getLastName()))
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName));
    }

    @Override
    public Long getEmployeeCountByDepartment(String departmentName) {
        departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName));

        return departmentRepository.countEmployeesInDepartment(departmentName);
    }

    @Override
    public String getSumOfDifferentDegreesWithinDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName));

        List<DepartmentDegreeCountDto> statisticList = departmentRepository.getDepartmentStatistics(department.getName());

        return statisticList.stream()
                .map(statistic -> String.format("%s - %d", statistic.getDegree().toString().toLowerCase() + "s", statistic.getCount()))
                .collect(Collectors.joining("\n"));
    }
}
