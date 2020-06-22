package com.neosoft;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.neosoft.controller.EmployeeController;
import com.neosoft.model.Emp_Address;
import com.neosoft.model.Emp_Education;
import com.neosoft.model.Employee_Detail;
import com.neosoft.model.Employee_Master;
import com.neosoft.model.Employee_Role;
import com.neosoft.model.Employee_contacts;
import com.neosoft.model.Employment_Detail;
import com.neosoft.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootPocRelation1ApplicationTests {

	@Test
	void contextLoads() {
	}

	private MockMvc mockMvc;
	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	List<Employee_Master> list = null;
	List<Employee_Master> list2 = null;
	List<Emp_Address> list4 = new ArrayList();;
	Employee_Master em1 = null;
	Employee_Master em2 = null;
	Employee_Master em3 = null;
	Employee_Master em4 = null;
	Employee_Detail edetail = null;
	Emp_Address add = null;
	String searchby = null;
	Employee_Role er = null;
	Employment_Detail emp_d = null;
	float per = 45f;
	Date d = new Date(2020, 06, 18);
	Emp_Education emp_Educa = null;
	Employee_contacts employee_cont = null;
	List<Emp_Education> edlist = new ArrayList();
	Long id;

	@BeforeEach
	public void setUpForTestin() {

		list = new ArrayList<Employee_Master>();

		emp_d = new Employment_Detail(null, d, "pooja", d, 1, d, 50000, "ddddd", 5.0f, em1);
		er = new Employee_Role(null, d, "pooja", d, 1, "admin", em1);
		edetail = new Employee_Detail(null, d, "pooja", d, 1, "pooja", "gupta", "aaaaa", d, true, em1);
		add = new Emp_Address(null, d, "pooja", d, 1, "office", "dhar", "dhar", "mp", "india", 232323, em1);
		emp_Educa = new Emp_Education(null, d, "pooja", d, 1, "bsc", "abc", "abc", per, d, null);
		edlist.add(emp_Educa);
		list4.add(add);
		employee_cont = new Employee_contacts(null, d, "pooja", d, 1, 999999999, 8888888, "sss@gmail.com", em1);
		em1 = new Employee_Master(null, d, null, d, 100, "poojab", "poojab", true, edlist, list4, edetail, emp_d, er,
				employee_cont);
		em2 = new Employee_Master(null, d, null, d, 101, "artib", "1111", true, edlist, list4, edetail, emp_d, er,
				employee_cont);
		em3 = new Employee_Master(null, d, null, d, 102, "aaaaa", "777777", false, edlist, list4, edetail, emp_d, er,
				employee_cont);
		emp_d.setEmployment_Detail(em1);
		er.setEmployee_Role(em1);
		edetail.setEmployee_detail(em1);
		add.setMaster(em1);
		emp_Educa.setMaster(em1);
		employee_cont.setEmp_contacts(em1);
		list.add(em1);
		list.add(em2);
		id = 10l;

		list2.add(em3);

	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	// test to select all employee
//	@Test
//	public void testGetEmployees() throws Exception {
//		when(employeeService.getEmployees()).thenReturn(list);
//
//		mockMvc.perform(get("/employees")).andExpect(status().isOk());
//
//		assertEquals(list, employeeService.getEmployees());
//
//	}

	// test to search employee
//	@Test
//	public void testGetemployee() throws Exception {
//		searchby = "dob";
//		when(employeeService.getEmployee(searchby)).thenReturn(list);
//
//		mockMvc.perform(get("/employees/dob")).andExpect(status().isOk());
//		assertEquals(list, employeeService.getEmployee(searchby));
//	}

	// test to update employee
//	@Test
//	public void testUpdateEmployee() throws Exception {
//		em1.setPassword("444444444");
//		when(employeeService.updateEmployee(em1, id)).thenReturn(em1);
//
//		mockMvc.perform(get("/employee/id")).andExpect(status().isOk());
//		assertEquals(em1, employeeService.updateEmployee(em1, id));
//	}

	// test to save employee
	@Test
	public void testSaveEmployee() throws Exception {
//		em4 = new Employee_Master(null, d, null, d, 100, "arti", "sharma", true, edlist, list4, edetail, emp_d, er,
//				employee_cont);
//		emp_d.setEmployment_Detail(em4);
//		er.setEmployee_Role(em4);
//		edetail.setEmployee_detail(em4);
//		add.setMaster(em4);
//		emp_Educa.setMaster(em4);
//		employee_cont.setEmp_contacts(em4);
		System.out.println();
		when(employeeController.saveEmployee(em1)).thenReturn(em1);

		mockMvc.perform(get("/employee")).andExpect(status().isOk());
		assertEquals(em1, employeeController.saveEmployee(em1));
	}

	// test to delete employee
//	@Test
//	public void testDeleteEmployee() throws Exception {
//
//		when(employeeService.deleteEmployee(id)).thenReturn("Employee" + id + " sucsessfully deleted");
//
//		mockMvc.perform(get("/employee/id")).andExpect(status().isOk());
//		assertEquals("Employee" + id + " sucsessfully deleted", employeeService.deleteEmployee(id));
//	}

	// test to soft delete employee
//	@Test
//	public void testDeactivate() throws Exception {
//		id = 102l;
//		when(employeeService.deactivate(id)).thenReturn(em2);
//
//		mockMvc.perform(get("/deactivate/id")).andExpect(status().isOk());
//		assertEquals(em2, employeeService.deactivate(id));
//	}

	// test to get employee by id
//	@Test
//	public void testgetEmployee() throws Exception {
//		when(employeeService.getEmployee(id)).thenReturn(em1);
//
//		mockMvc.perform(get("/employee/id")).andExpect(status().isOk());
//		assertEquals(em1, employeeService.getEmployee(id));
//	}

	// test to get active employee
//	@Test
//	public void testGetActiveEmployee() throws Exception {
//
//		when(employeeService.getActiveEmployee()).thenReturn(list);
//
//		mockMvc.perform(get("/activeEmployees")).andExpect(status().isOk());
//		assertEquals(list, employeeService.getActiveEmployee());
//
//	}

	// test to get DEactive employee
//	@Test
//	public void testGetDeactiveEmployee() throws Exception {
//
//		when(employeeService.getDeactiveEmployee()).thenReturn(list2);
//
//		mockMvc.perform(get("/deactiveEmployees")).andExpect(status().isOk());
//		assertEquals(list, employeeService.getDeactiveEmployee());
//	}

}
