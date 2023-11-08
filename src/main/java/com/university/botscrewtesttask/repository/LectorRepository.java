package com.university.botscrewtesttask.repository;

import com.university.botscrewtesttask.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("SELECT AVG(l.salary) " +
            "FROM Lector l" +
            " JOIN l.departments d" +
            " WHERE d.name = :departmentName")
    Optional<BigDecimal> findAverageSalary(@Param("departmentName") String departmentName);

    @Query("SELECT l " +
            "FROM Lector l " +
            "WHERE CONCAT(l.firstName, ' ', l.lastName) " +
            "LIKE %:template%")
    List<Lector> findLectorsByTemplate(@Param("template") String template);
}
