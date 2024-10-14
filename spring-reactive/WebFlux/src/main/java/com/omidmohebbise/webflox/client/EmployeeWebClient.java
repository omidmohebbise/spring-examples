package com.omidmohebbise.webflox.client;

import com.omidmohebbise.webflox.model.Employee;
import com.omidmohebbise.webflox.model.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class EmployeeWebClient implements CommandLineRunner {

    private final EmployeeRepository repository;

    public EmployeeWebClient(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void consumeFluxStream() {
        Thread thread = new Thread(() -> {
            Flux<Employee> fluxStream = repository.findAll().delayElements(Duration.of(3, ChronoUnit.SECONDS));
            fluxStream.doOnNext(System.out::println)
                    .subscribe();
        });
        thread.start();
    }

    @Override
    public void run(String... args) throws Exception {
        consumeFluxStream();

    }
}
