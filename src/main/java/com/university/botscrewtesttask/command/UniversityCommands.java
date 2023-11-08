package com.university.botscrewtesttask.command;

import com.university.botscrewtesttask.service.DepartmentService;
import com.university.botscrewtesttask.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShellComponent
@RequiredArgsConstructor
public class UniversityCommands {
    private final DepartmentService departmentService;
    private final LectorService lectorService;
    private static final Pattern INPUT_PATTERN = Pattern.compile("^\\w+\\sstatistics$");

    @ShellMethod(value = "Show the head of the department", key = "Who is head of department")
    public String showHeadOfDepartment(@ShellOption(help = "Department name") String departmentName) {
        return departmentService.getHeadOfDepartment(departmentName);
    }

    @ShellMethod(value = "Show the department statistics", key = "Show")
    public String showDepartmentStatistics(
            @ShellOption(help = "Department name") String departmentName,
            @ShellOption(help = "Statistics placeholder") String statisticsPlaceholder
    ) {
        String input = String.join(" ", departmentName, statisticsPlaceholder);
        Matcher matcher = INPUT_PATTERN.matcher(input);

        if (matcher.matches()) {
            return departmentService.getSumOfDifferentDegreesWithinDepartment(departmentName);
        } else {
            throw new RuntimeException("Invalid or missing department or statistics placeholder");
        }
    }

    @ShellMethod(value = "Show the average salary for the department", key = "Show the average salary for the department")
    public String showAvgDepartmentSalary(@ShellOption(help = "Department name") String departmentName) {
        return lectorService.findAverageSalaryByDepartmentName(departmentName);
    }

    @ShellMethod(value = "Show count of Lectors for the department", key = "Show count of employee for")
    public Long showLectorsCountForDepartment(@ShellOption(help = "Department name") String departmentName) {
        return departmentService.getEmployeeCountByDepartment(departmentName);
    }

    @ShellMethod(value = "Show Lectors by template", key = "Global search by")
    public String showLectorsByTemplate(@ShellOption(help = "Template") String template) {
        return lectorService.getLectorsByTemplate(template);
    }
}

