package com.example.demo.controller;

import com.example.demo.model.BankUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/bank/user/")
public class BankUserController {

    private final Map<Integer, BankUser> db = new HashMap<>();

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<List<BankUser>> getUser() {
        List<BankUser> result = new ArrayList<BankUser>();
        result.addAll(db.values());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<BankUser> getDetailUser(@PathVariable int id) {
        BankUser result = db.get(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> addBankUser(@RequestBody BankUser request) {
        db.put(request.getId(), request);
        return ResponseEntity.ok("created!");
    }

    @PatchMapping(value = "/topup/{id}", produces = "application/json")
    public ResponseEntity<String> topupBalance(@PathVariable int id, @RequestBody BankUser request) {
        Boolean isExist = db.containsKey(id);
        if (!isExist) {
            return ResponseEntity.ok("data not found");
        }

        BankUser balanceData = db.get(id);
        balanceData.setBalance(request.getBalance() + balanceData.getBalance());
        db.put(balanceData.getId(), balanceData);

        return ResponseEntity.ok("topup success!");
    }

    @PatchMapping(value = "/pay/{id}", produces = "application/json")
    public ResponseEntity<String> pay(@PathVariable int id, @RequestBody BankUser request) {
        Boolean isExist = db.containsKey(id);
        if (!isExist) {
            return ResponseEntity.ok("data not found");
        }

        BankUser balanceData = db.get(id);

        if (request.getBalance() > balanceData.getBalance()) {
            return ResponseEntity.ok("your balance is not enough");
        }

        balanceData.setBalance(balanceData.getBalance() - request.getBalance());
        db.put(balanceData.getId(), balanceData);

        return ResponseEntity.ok("payment success!");
    }

}
