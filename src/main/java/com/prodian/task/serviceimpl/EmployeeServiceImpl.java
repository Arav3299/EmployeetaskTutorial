package com.prodian.task.serviceimpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.prodian.task.dto.EmployeeDto;
import com.prodian.task.model.Employee;
import com.prodian.task.repository.EmployeeRepository;
import com.prodian.task.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto postEmployee(Employee employee) {

		// country code checking	
		Map<String, String> map = codeChecking();

		String country = employee.getCountry();
		// System.out.println(code);

		Iterator<String> iterator = map.keySet().iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();
			// System.out.println(key);
			if (key.equalsIgnoreCase(country)) {

				employee.setMobileNumber(map.get(key) + "-" + employee.getMobileNumber());
			}

		}

		// employee id generation
		String employeeIdWithName = "";
		int count = 0;

		String[] nameArray = employee.getName().split(" ");

		for (int i = 0; i < nameArray.length; i++) {
			employeeIdWithName += nameArray[count].charAt(0);
			count++;
		}

		employee.setEmployeeId(employeeIdWithName.toUpperCase() + employee.getEmployeeId());

		employeeRepository.save(employee);

		return convertEntityToDto(employee);

	}

	// for storing some country code with name
	private Map<String, String> codeChecking() {

		Map<String, String> codeMap = new HashMap<>();

		codeMap.put("india", "+91");
		codeMap.put("malta", "+356");
		codeMap.put("usa", "+1");
		codeMap.put("uk", "+44");
		codeMap.put("australia", "+61");

		return codeMap;
	}

	// converting employee to dto
	private EmployeeDto convertEntityToDto(Employee employee) {

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setCountry(employee.getCountry());
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setId(employee.getId());
		employeeDto.setMobileNumber(employee.getMobileNumber());
		employeeDto.setName(employee.getName().split(" "));

		return employeeDto;

	}

	@Override
	public Map<String, String> save(Employee employee) {

		// country code checking
		Map<String, String> map = codeChecking();

		String country = employee.getCountry();
		// System.out.println(code);

		Iterator<String> iterator = map.keySet().iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();
			// System.out.println(key);
			if (key.equalsIgnoreCase(country)) {

				employee.setMobileNumber(map.get(key) + "-" + employee.getMobileNumber());
			}

		}

		// employee code generation
		String employeeIdWithName = "";
		int count = 0;

		String[] nameArray = employee.getName().split(" ");

		for (int i = 0; i < nameArray.length; i++) {
			employeeIdWithName += nameArray[count].charAt(0);
			count++;
		}

		employee.setEmployeeId(employeeIdWithName.toUpperCase() + employee.getEmployeeId());

		employeeRepository.save(employee);
		
		Map<String, String> returningMap = new HashMap<>();
		int inc =0;
		for(String name :nameArray) {
			
			if(inc==0) {
				returningMap.put("name",name);
				inc++;
			}
			else {
				returningMap.put("name"+inc,name);
				inc++;
		}
		}
		returningMap.put("employeeId", employee.getEmployeeId());
		returningMap.put("country", employee.getCountry());
		returningMap.put("mobileNumber", employee.getMobileNumber());
		
		String id = Integer.toString(employee.getId());
		returningMap.put("id",id);
		
		return returningMap;
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		 
		Employee oldEmployee = employeeRepository.findById(id).get();
		
		oldEmployee.setCountry(employee.getCountry());
		oldEmployee.setEmployeeId(employee.getEmployeeId());
		oldEmployee.setMobileNumber(employee.getMobileNumber());
		oldEmployee.setName(employee.getName());
		
		return employeeRepository.save(oldEmployee);
		
		 
		 
	}

	@Override
	public void deleteEmployee(int id) {
		
		employeeRepository.deleteById(id);
		
	}

	 

}
