package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class DemoApplication {

	@Autowired
	private EmployeeService service;

	//this method can be accessed by user whose role is user
	@GetMapping("/{employeeId}")
	@RolesAllowed("user")
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
		return ResponseEntity.ok(service.getEmployee(employeeId));
	}

	//this method can be accessed by user whose role is admin
	@GetMapping
	@RolesAllowed("admin")
	public ResponseEntity<List<Employee>> findALlEmployees() {
		return ResponseEntity.ok(service.getAllEmployees());
	}

	@GetMapping("/test")
	public String str() {
		return "Nguyen Dinh Hai";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}