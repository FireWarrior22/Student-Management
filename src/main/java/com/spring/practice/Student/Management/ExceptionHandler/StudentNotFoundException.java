package com.spring.practice.Student.Management.ExceptionHandler;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message) {
        super(message);
    }
}
