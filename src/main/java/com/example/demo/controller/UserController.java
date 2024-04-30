package com.example.demo.controller;

import com.example.demo.common.dto.user.UpdateUserRequest;
import com.example.demo.intergaces.IUserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<User>> getUser() {
        List<User> result = service.getUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<User> getDetailUser(@PathVariable int id) {
        User result = service.getUser(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User request) {
        User result = service.createUser(request);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        User result = service.updateUser(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean result = service.deleteUser(id);
        if (!result) return ResponseEntity.ok("invalid id!");

        return ResponseEntity.ok("deleted!");
    }

}
