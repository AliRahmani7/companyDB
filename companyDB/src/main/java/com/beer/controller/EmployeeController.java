package com.beer.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beer.exception.RecordNotFoundException;
import com.beer.model.Employee;
import com.beer.service.EmployeeService;
 
@RestController
//@RequestMapping("")
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;
 
    @GetMapping("/")										
	String testServer()
    {
	    return "Server Running !";
	}
    @PostMapping("/addEmployee")
	public Employee create(@RequestBody Employee employee) throws RecordNotFoundException 
	{
		return employeeService.createUpdateEmployee(employee);
	}
	
	@GetMapping("/allEmployees")
	public List<Employee> getAll() 
	{
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getById(@PathVariable Long id) throws RecordNotFoundException 
	{
		return employeeService.getEmployee(id);
	}
	
	@PutMapping("/employee/{id}") 
	public Employee update(@PathVariable Long id, @RequestBody Employee employee) throws RecordNotFoundException
	{     
        if(employeeService.getEmployee(id) !=null)
        	employee = employeeService.createUpdateEmployee(employee);
             
        return employee;
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws RecordNotFoundException
	{   
		 if(employeeService.getEmployee(id) !=null)
			 employeeService.deleteEmployee(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
   /* List<Employee> loadLocalData()
    {
    	List<Employee> list=new ArrayList<Employee>();
    	Employee employee1= new Employee(1L,"Elizabeth", "Dean", "abc@gmail.com");
    	list.add(employee1);
    	Employee employee2= new Employee(2L,"John", "Doe", "xyz@gmail.com");
    	list.add(employee2);
    	return list;
    }*/
}