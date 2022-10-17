package com.prodian.task.repository;

import org.springframework.data.repository.CrudRepository;

import com.prodian.task.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
