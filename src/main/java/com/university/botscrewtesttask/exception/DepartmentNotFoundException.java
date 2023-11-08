package com.university.botscrewtesttask.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String fieldValue) {
        super(String.format("Department not found with name: %s", fieldValue));
    }
}
