package com.omidmohebbise.sleuth.controller;


import com.omidmohebbise.sleuth.service.UserService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResource> findAll() {
        return userService.findAll().stream().map(user -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(user.getId()).withSelfRel();
            UserResource userResource = new UserResource(user.getId(), user.getFullName());
            userResource.add(selfLink);
            return userResource;
        }).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public UserResource findById(@PathVariable int id) {
        var user = userService.findById(id);
        UserResource userResource = new UserResource(user.getId(), user.getFullName());
        Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(userResource.getId()).withSelfRel();
        userResource.add(selfLink);
        userResource.add(selfLink.withRel("delete")
                .withType("Delete")
                .withMedia("Application/Json")
                .withName("delete method")
                .withProfile("test")
                );
        return userResource;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok("");

    }

}
