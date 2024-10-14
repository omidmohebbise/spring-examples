package com.omidmohebbise.firstappkt.firstappkotlin.controller

import com.omidmohebbise.firstappkt.firstappkotlin.entity.PersonEO
import com.omidmohebbise.firstappkt.firstappkotlin.repository.PersonJpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@CrossOrigin
@RequestMapping("/persons")
class PersonController (private val repository: PersonJpaRepository){

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: Long): ResponseEntity<PersonEO>{
        return ResponseEntity(repository.findById(userId).orElse(null) ,HttpStatus.OK);
    }
}