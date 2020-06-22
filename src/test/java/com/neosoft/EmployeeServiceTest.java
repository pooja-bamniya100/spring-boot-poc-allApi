package com.neosoft;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.neosoft.controller.EmployeeController;
import com.neosoft.dao.Employee_MasterRepository;
import com.neosoft.model.Emp_Address;
import com.neosoft.model.Employee_Master;
import com.neosoft.service.EmployeeService;

/**
 * 
 * 
 * It also not executable
 * 
 * */
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

	private MockMvc mockMvc; 
	@Mock
	private Employee_MasterRepository masterRepo;

	@InjectMocks
	private EmployeeService employeeService;

	List<Employee_Master> list=null;
	List<Employee_Master> list2=null;
	List<Emp_Address> list4=null;
	Employee_Master em1=null;
	Employee_Master em2=null;
	Employee_Master em3=null;
	Employee_Master em4=null;
	Emp_Address add=null;
	String searchby=null;
	Long id;
	@BeforeEach
	public void setUpForTestin(){
		list=new ArrayList<Employee_Master>();
		Date d= new Date(2020, 06, 18);
		em1=new Employee_Master(null,d, null, d,100,"poojab","poojab", true,null, null, null,null, null, null) ;
		em2 =new Employee_Master(null,d, null, d,101,"artib","1111", true,null, null, null,null, null, null) ;
		em3 =new Employee_Master(null,d, null, d,102,"aaaaa","777777", false,null, null, null,null, null, null) ;
		list.add(em1);
		list.add(em2);
		id=10l;

		list2.add(em3);

	}
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeService)
				.build();
	}
		@Test
	public void testGetEmployees() throws Exception {
		when(employeeService.getEmployees()).thenReturn(list);

		mockMvc.perform(get("/employees"))
		.andExpect(status().isOk());

		assertEquals(list,employeeService.getEmployees());

	}
	//test to search employee
	@Test
	public void testGetemployee() throws Exception
	{
		searchby="dob";
		when(employeeService.getEmployee(searchby)).thenReturn(list);

		mockMvc.perform(get("/employees/dob"))
		.andExpect(status().isOk()); 
		assertEquals(list,employeeService.getEmployee(searchby));
	}
	//test to update employee
	@Test
	public void testUpdateEmployee() throws Exception
	{
		em1.setPassword("444444444");
		when(employeeService.updateEmployee(em1,id)).thenReturn(em1);

		mockMvc.perform(get("/employee/id"))
		.andExpect(status().isOk()); 
		assertEquals(em1,employeeService.updateEmployee(em1,id));
	}

	//test to save employee
	@Test
	public void testSaveEmployee() throws Exception
	{
		em4=new Employee_Master();
		add=new Emp_Address();
		add.setCity("indore");add.setAddType("office");add.setDist("indore");add.setCountry("india");add.setState("mp");add.setPincode(888888);
		list4=new ArrayList();
		em4.setActive(true);
		em4.setEmp_id(105l);
		em4.setPassword("444444444");
		em4.setUsername("poojabamniya");
		add.setMaster(em4);
		list.add(em4);
		em4.setAddress(list4);

		when(employeeService.saveEmployee(em4)).thenReturn(em4);

		mockMvc.perform(get("/employee"))
		.andExpect(status().isOk()); 
		assertEquals(em4,employeeService.saveEmployee(em4));
	}

	//test to delete employee
	@Test
	public void testDeleteEmployee() throws Exception
	{

		when(employeeService.deleteEmployee(id)).thenReturn("Employee" + id + " sucsessfully deleted");

		mockMvc.perform(get("/employee/id"))
		.andExpect(status().isOk()); 
		assertEquals("Employee" + id + " sucsessfully deleted",employeeService.deleteEmployee(id));
	}
	//test to soft delete employee
	@Test
	public void testDeactivate() throws Exception
	{
		id=102l;
		when(employeeService.deactivate(id)).thenReturn(em2);

		mockMvc.perform(get("/deactivate/id"))
		.andExpect(status().isOk()); 
		assertEquals(em2,employeeService.deactivate(id));
	}
	//test to get employee by id
	@Test
	public void testgetEmployee() throws Exception
	{
		when(employeeService.getEmployee(id)).thenReturn(em1);

		mockMvc.perform(get("/employee/id"))
		.andExpect(status().isOk()); 
		assertEquals(em1,employeeService.getEmployee(id));
	}
	//test to get active employee
	@Test
	public void testGetActiveEmployee() throws Exception
	{

		when(employeeService.getActiveEmployee()).thenReturn(list);

		mockMvc.perform(get("/activeEmployees"))
		.andExpect(status().isOk()); 
		assertEquals(list,employeeService.getActiveEmployee());

	}
	//test to get DEactive employee
	@Test
	public void testGetDeactiveEmployee() throws Exception
	{

		when(employeeService.getDeactiveEmployee()).thenReturn(list2);

		mockMvc.perform(get("/deactiveEmployees"))
		.andExpect(status().isOk()); 
		assertEquals(list,employeeService.getDeactiveEmployee());
	}
	// test to select all employee
//		@Test
//		public void testGetEmployees() throws Exception {
//			when(masterRepo.findAll()).thenReturn(list);
	//
//			mockMvc.perform(get("/employees"))
//			.andExpect(status().isOk());
	//
//			assertEquals(list,masterRepo.findAll());
	//
//		}
//		//test to search employee
//		@Test
//		public void testGetemployee() throws Exception
//		{
//			searchby="dob";
//			when(masterRepo.findByFirstNameOrLastNamePincode(searchby)).thenReturn(list);
	//
//			mockMvc.perform(get("/employees/dob"))
//			.andExpect(status().isOk()); 
//			assertEquals(list,masterRepo.findByFirstNameOrLastNamePincode(searchby));
//		}
//		//test to update employee
//		@Test
//		public void testUpdateEmployee() throws Exception
//		{
//			em1.setPassword("444444444");
//			when(masterRepo.save(em1)).thenReturn(em1);
	//
//			mockMvc.perform(get("/employee/id"))
//			.andExpect(status().isOk()); 
//			assertEquals(em1,masterRepo.save(em1));
//		}
	//
//		//test to save employee
//		@Test
//		public void testSaveEmployee() throws Exception
//		{
//			em4=new Employee_Master();
//			add=new Emp_Address();
//			add.setCity("indore");add.setAddType("office");add.setDist("indore");add.setCountry("india");add.setState("mp");add.setPincode(888888);
//			list4=new ArrayList();
//			em4.setActive(true);
//			em4.setEmp_id(105l);
//			em4.setPassword("444444444");
//			em4.setUsername("poojabamniya");
//			add.setMaster(em4);
//			list.add(em4);
//			em4.setAddress(list4);
	//
//			when(masterRepo.save(em4)).thenReturn(em4);
	//
//			mockMvc.perform(get("/employee"))
//			.andExpect(status().isOk()); 
//			assertEquals(em4,masterRepo.save(em4));
//		}
	//
//		//test to delete employee
//		@Test
//		public void testDeleteEmployee() throws Exception
//		{
//			mockMvc.perform(get("/employee/id"))
//			.andExpect(status().isOk()); 
//			masterRepo.deleteById(id);
//			verify(masterRepo, times(1)).deleteById(id);
//		}
//		//test to soft delete employee
//		@Test
//		public void testDeactivate() throws Exception
//		{
//			em2.setActive(false);
//			when(masterRepo.save(em2)).thenReturn(em2);
	//
//			mockMvc.perform(get("/deactivate/id"))
//			.andExpect(status().isOk()); 
//			assertEquals(em2,masterRepo.save(em2));
//		}
//		//test to get employee by id
//		@Test
//		public void testgetEmployee() throws Exception
//		{
//			when(employeeService.getEmployee(id)).thenReturn(em1);
	//
//			mockMvc.perform(get("/employee/id"))
//			.andExpect(status().isOk()); 
//			assertEquals(em1,employeeService.getEmployee(id));
//		}
//		//test to get active employee
//		@Test
//		public void testGetActiveEmployee() throws Exception
//		{
	//
//			when(masterRepo.findAllByStatus(true)).thenReturn(list);
	//
//			mockMvc.perform(get("/activeEmployees"))
//			.andExpect(status().isOk()); 
//			assertEquals(list,masterRepo.findAllByStatus(true));
	//
//		}
//		//test to get DEactive employee
//		@Test
//		public void testGetDeactiveEmployee() throws Exception
//		{
	//
//			when(masterRepo.findAllByStatus(false)).thenReturn(list2);
	//
//			mockMvc.perform(get("/deactiveEmployees"))
//			.andExpect(status().isOk()); 
//			assertEquals(list,masterRepo.findAllByStatus(false));
//		}
	//


}
