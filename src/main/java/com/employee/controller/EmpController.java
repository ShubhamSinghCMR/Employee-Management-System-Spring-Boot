package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.entity.Employee;
import com.employee.service.EmpService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService Service;
	
	@GetMapping("/")
	public String home(org.springframework.ui.Model m)
	{
		List<Employee> emp=Service.getAllEmp();
		m.addAttribute("emp",emp);
		return "index";
	}
	@GetMapping("/addemp")
	public String addEmpForm()
	{
		return "add_emp";
	}
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session)
	{
		System.out.println(e);
		session.setAttribute("msg", "Employee added");
		Service.addEmp(e);
		
		return "redirect:/";
	}
	@GetMapping("/edit/{id}")
	public String editEmp(@PathVariable int id, org.springframework.ui.Model m)
	{
		Employee e=Service.getEmpById(id);
		m.addAttribute("emp",e);
		return "edit_emp";
	}
	@PostMapping("/update")
	public String empUpdate(@ModelAttribute Employee e, HttpSession session)
	{
		System.out.println(e);
		session.setAttribute("msg", "Employee details updated");
		Service.addEmp(e);
		
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String delEmp(@PathVariable int id)
	{
		Service.delEmpById(id);
		return "redirect:/";
	}
}
