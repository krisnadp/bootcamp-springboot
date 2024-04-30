package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final Map<Integer, User> db = new HashMap<>();

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<List<User>> getUser() {
        List<User> result = new ArrayList<User>();
        for (User i : db.values()) {
            result.add(i);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<User> getDetailUser(@PathVariable int id) {
        User result = db.get(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody User request) {
        db.put(request.getId(), request);
        return ResponseEntity.ok("created!");
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User request) {
        Boolean isExist = db.containsKey(id);
        if (!isExist) {
            return ResponseEntity.ok("data not found");
        }
        User currentData = db.get(id);
        currentData.setName(request.getName());
        currentData.setNim(request.getNim());
        currentData.setProdi(request.getProdi());
        db.put(currentData.getId(), currentData);
        return ResponseEntity.ok("updated!");
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        db.remove(id);
        return ResponseEntity.ok("deleted!");
    }

}
