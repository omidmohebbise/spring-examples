package com.spdeveloper.cassandra.controller;

import com.spdeveloper.cassandra.service.PersonsService;
import com.spdeveloper.cassandra.service.dto.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonsService personsService;

    public PersonController(PersonsService personsService) {
        this.personsService = personsService;
    }
    @PostMapping
    public ResponseEntity<?> insert (@RequestBody PersonDTO dto){
        return ResponseEntity.ok(personsService.insert(dto).getId());
    }
    @GetMapping
    public ResponseEntity<?> findAll (){
        return ResponseEntity.ok(personsService.findAll());
    }
}
