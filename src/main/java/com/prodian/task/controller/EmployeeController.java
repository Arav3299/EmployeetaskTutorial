package com.prodian.task.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodian.task.dto.EmployeeDto;
import com.prodian.task.model.Employee;
import com.prodian.task.repository.EmployeeRepository;
import com.prodian.task.service.EmployeeService;

@RestController
@RequestMapping("/task/employee")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;

	
	@PostMapping("/post")
	private ResponseEntity<EmployeeDto> postEmployee(@RequestBody Employee employee) {

		try {

			return new ResponseEntity<EmployeeDto>(employeeService.postEmployee(employee), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping("/save")
	private ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		// Map<String,String> map = ;
		Map<String, String> sortedMap = new TreeMap<String, String>(employeeService.save(employee));
		return ResponseEntity.ok(sortedMap);

	}

//	@Scheduled(fixedDelay=2000)
//	private void hi() throws InterruptedException {
//
//		System.out.println("hi arav");
//		System.out.println(new Date());
//		Thread.sleep(1000);
//
//	}
	
	@GetMapping("/get")
	private List<Employee> get(){
		List<Employee> emp = (List<Employee>) employeeRepository.findAll();
		return emp;
	}
	
//	@PutMapping("/edit")
//	private ResponseEntity<Employee> update(@RequestBody Employee employee){
//		 
//		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee),HttpStatus.OK);
//		
//	}
	
	@GetMapping("/get/{id}")
	private Employee getEmployeeById(@PathVariable("id")int id) {
		
		return employeeRepository.findById(id).get();
		
	}
	
	@PutMapping("edit/{id}")
	private ResponseEntity<Employee> updateEmployee(@PathVariable("id")int id,@RequestBody Employee employee){
		//System.out.println("entered");
		return new ResponseEntity<Employee>(employeeService.updateEmployee(id,employee),HttpStatus.OK);
		
	}
	
	@DeleteMapping("dlt/{id}")
	private void deleteEmployee(@PathVariable("id") int id) {
		employeeService.deleteEmployee(id);
	}

}
