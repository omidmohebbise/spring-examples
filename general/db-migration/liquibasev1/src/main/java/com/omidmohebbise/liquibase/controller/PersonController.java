package com.omidmohebbise.liquibase.controller;


import com.omidmohebbise.liquibase.service.AddPerson;
import com.omidmohebbise.liquibase.service.ListPerson;
import com.omidmohebbise.liquibase.service.dto.PersonFilterDto;
import com.omidmohebbise.liquibase.service.dto.SortDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class PersonController {

    private AddPerson addPerson;
    private ListPerson listPerson;

    public PersonController(AddPerson addPerson, ListPerson listPerson) {
        this.addPerson = addPerson;
        this.listPerson = listPerson;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> list(){
        List<SortDto> sortDtos = new ArrayList<>();
        sortDtos.add(new SortDto("firstname","asc"));
        return ResponseEntity.ok(listPerson.list(0,100, new PersonFilterDto(
                sortDtos,null,null,null
        )));
    }
    @GetMapping(path = "/add")
    public ResponseEntity<?> add(){
        return ResponseEntity.ok(addPerson.add());
    }
}
