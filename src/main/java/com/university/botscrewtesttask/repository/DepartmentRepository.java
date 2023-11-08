package com.university.botscrewtesttask.repository;

import com.university.botscrewtesttask.dto.DepartmentDegreeCountDto;
import com.university.botscrewtesttask.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);

    @Query("SELECT COUNT(l)" +
            "FROM Lector l " +
            "JOIN l.departments department " +
            "WHERE department.name = :departmentName")
    Long countEmployeesInDepartment(@Param("departmentName") String departmentName);

    @Query("SELECT new com.university.botscrewtesttask.dto.DepartmentDegreeCountDto(l.degree, COUNT(l)) " +
            "FROM Lector l " +
            "JOIN l.departments d " +
            "WHERE d.name = :departmentName " +
            "GROUP BY l.degree")
    List<DepartmentDegreeCountDto> getDepartmentStatistics(String departmentName);
}
