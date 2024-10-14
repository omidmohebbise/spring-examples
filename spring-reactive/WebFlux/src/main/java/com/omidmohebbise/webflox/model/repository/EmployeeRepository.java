package com.omidmohebbise.webflox.model.repository;

import com.omidmohebbise.webflox.model.Employee;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee , Long> {

    @Tailable
    Flux<Employee> findAllOrderByFullName();

}
