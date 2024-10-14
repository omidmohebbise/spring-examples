package com.omidmohebbise.aspect.controller;

import com.omidmohebbise.aspect.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class SimpleRestController {

    private final PersonService personService;

    public SimpleRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/hi")
    public String printHelloWorld() {
        System.out.println("hello from controller");
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity addPerson( @RequestBody PersonDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(dto.getName(), dto.getFamily()).getId());
    }

    @AllArgsConstructor
    @Getter
    public static class PersonDTO {
        private String name;
        private String family;
    }


}
