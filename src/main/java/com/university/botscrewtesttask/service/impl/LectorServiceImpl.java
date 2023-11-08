package com.university.botscrewtesttask.service.impl;

import com.university.botscrewtesttask.repository.LectorRepository;
import com.university.botscrewtesttask.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;

    @Override
    public String findAverageSalaryByDepartmentName(String departmentName) {
        BigDecimal avgSalary = lectorRepository.findAverageSalary(departmentName)
                .orElseThrow(() -> new NoSuchElementException("Department " + departmentName + " does not exist or no lectors' salaries were found for this department!"));

        return String.format("The average salary of %s is %.2f", departmentName, avgSalary);
    }

    @Override
    public String getLectorsByTemplate(String template) {
        return lectorRepository.findLectorsByTemplate(template).stream()
                .map(lector -> lector.getFirstName() + " " + lector.getLastName())
                .collect(joining(", "));
    }
}
