package com.omidmohebbise.springmongo.person;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
@Validated
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> list(@RequestParam(name = "lastName", required = false) String lastName) {
        if (lastName == null || lastName.isBlank()) {
            return personService.findAll();
        }
        return personService.findByLastName(lastName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable String id) {
        return personService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody CreatePersonRequest request) {
        Person created = personService.create(request.firstName(), request.lastName(), request.age());
        return ResponseEntity
                .created(URI.create("/api/people/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable String id, @Valid @RequestBody UpdatePersonRequest request) {
        return personService.update(id, request.firstName(), request.lastName(), request.age())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = personService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    public record CreatePersonRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotNull @Min(0) @Max(150) Integer age
    ) {
    }

    public record UpdatePersonRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotNull @Min(0) @Max(150) Integer age
    ) {
    }
}

