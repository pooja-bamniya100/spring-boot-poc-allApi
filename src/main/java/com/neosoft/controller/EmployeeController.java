package com.neosoft.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.dao.Employee_MasterRepository;
import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.model.Employee_Master;
import com.neosoft.service.EmployeeService;


@RestController
@Transactional
public class EmployeeController {


	@Autowired
	EmployeeService employeeService;


	/**
	 * Create Employee_Master employee.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	@PostMapping(value="/employee")
	public Employee_Master saveEmployee(@Valid @RequestBody Employee_Master employee) {


		return employeeService.saveEmployee(employee);
	}

	/**
	 * Delete Employee 
	 *
	 * @param id the employee id
	 * @return the message
	 * @throws ExceptResourceNotFoundExceptionion the exception
	 */

	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable Long id) throws ResourceNotFoundException
	{
		String msg=null;
		try {
			msg= employeeService.deleteEmployee(id);
		}
		catch(Exception ex)
		{
			throw new ResourceNotFoundException("Employee not found having id "+id);
		}
		return msg;


	}

	/**deactivate the employee
	 * 
	 @param id the employee id
	 * @return the deavtive employee
	 * @throws ExceptResourceNotFoundExceptionion the exception
	 */

	@DeleteMapping("/deactivate/{id}")
	public Employee_Master deactivate(@PathVariable Long id) throws ResourceNotFoundException
	{

		return  employeeService.deactivate(id);


	}
	/**
	 * Update Employee details
	 *
	 * @param employee the employee
	 * @return the employee
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/employee/{id}")
	public Employee_Master updateEmployee(@RequestBody Employee_Master employee,@PathVariable long id) throws ResourceNotFoundException
	{
     

		return  employeeService.updateEmployee(employee,id);


	}


	/**
	 * Get all Employee sorted list .
	 * 
	 * @param orderBy order by acsending or descending
	 *@param sortBy sort by dob or dateOfjoin
	 * @return the list
	 */
	@GetMapping("/sortedEmployees/{sortBy}/{orderBy}")
	public List<Employee_Master> getSortedEmployees(@PathVariable String sortBy,@PathVariable String orderBy)
	{
		return employeeService.getSortedEmployees(sortBy,orderBy);
	}


	/**
	 * Get all search employee .
	 * 
	 *@param searchBy search by firstname or lastmane or pincode
	 * @return the list
	 */
	@GetMapping("/employees/{searchBy}") 
	public List<Employee_Master> getEmployee(@PathVariable ("searchBy") Object  searchBy)
			throws ResourceNotFoundException 
	{ 

		return employeeService.getEmployee(searchBy);

	}

	/**
	 * Get all Employee list.
	 *
	 * @return the list
	 */

	@GetMapping("/employees")
	public List<Employee_Master> getEmployees()
	{

		return employeeService.getEmployees();

	}
	/**
	 * Gets Employee by id.
	 *
	 * @param id the employee id
	 * @return the Employee by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/employee/{id}")
	public Employee_Master getEmployee(@PathVariable Long id) throws ResourceNotFoundException
	{

		return employeeService.getEmployee(id);

	}

	/**
	 * Gets Employee by status.
	 *
	 * @return the active employee
	 */
	@GetMapping("/activeEmployees")
	public List<Employee_Master> getActiveEmployee() throws ResourceNotFoundException
	{

		return employeeService.getActiveEmployee();

	}

	/**
	 * Gets Employee by status.
	 *
	 * @return the Deactive employee
	 */
	@GetMapping("/deactiveEmployees")
	public List<Employee_Master> getDeactiveEmployee()
	{

		return employeeService.getDeactiveEmployee();

	}
	

}
