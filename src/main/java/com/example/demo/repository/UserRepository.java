package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //    added find by nim in spring boot
//    User findByNim(String nim);
    User findById(int id);

}
