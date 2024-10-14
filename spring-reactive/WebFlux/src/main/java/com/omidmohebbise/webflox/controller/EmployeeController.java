package com.omidmohebbise.webflox.controller;

import com.omidmohebbise.webflox.model.Employee;
import com.omidmohebbise.webflox.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    private Mono<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping()
    private Flux<Employee> getAllEmployees() {
        return employeeService.findAll();
    }
}

