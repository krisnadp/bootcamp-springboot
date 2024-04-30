package com.example.demo.intergaces;

import com.example.demo.common.dto.user.UpdateUserRequest;
import com.example.demo.model.User;

import java.util.List;

public interface IUserService {

    User createUser(User request);

    User updateUser(int id, UpdateUserRequest request);

    boolean deleteUser(int id);
    List<User> getUsers();
    User getUser(int id);

}
