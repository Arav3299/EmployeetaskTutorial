package com.prodian.task.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prodian.task.dto.EmployeeDto;
import com.prodian.task.model.Employee;

public interface EmployeeService {

	EmployeeDto postEmployee(Employee employee);
	Map<String,String> save(Employee employee);
	Employee updateEmployee(int id, Employee employee);
	void deleteEmployee(int id);

}
