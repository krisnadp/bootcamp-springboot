package com.example.demo.service;

import com.example.demo.common.dto.user.UpdateUserRequest;
import com.example.demo.interfaces.IUserService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Map;

@Service
public class UserService implements IUserService {

    //    private final Map<Integer, User> db = new HashMap<>();
    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(User request) {
//        db.put(request.getId(), request);
        repository.save(request);
        return request;
    }

    @Override
    public User updateUser(int id, UpdateUserRequest request) {
//        User currentData = db.get(id);

        User currentData = repository.findById(id);

        if (currentData == null) {
            return null;
        }

        if (request.getName() != null) {
            currentData.setName(request.getName());
        }

        if (request.getNim() != null) {
            currentData.setNim(request.getNim());
        }

        if (request.getProdi() != null) {
            currentData.setProdi(request.getProdi());
        }
//        db.put(currentData.getId(), currentData);
        repository.save(currentData);
        return currentData;
    }

    @Override
    public boolean deleteUser(int id) {
//        User currentData = db.get(id);

        User currentData = repository.findById(id);

        if (currentData == null) {
            return false;
        }
        repository.delete(currentData);
        return true;
    }

    @Override
    public List<User> getUsers() {
//        List<User> result = new ArrayList<User>();
//        result.addAll(db.values());
        return repository.findAll();
    }

    @Override
    public User getUser(int id) {
//        return db.get(id);
        return repository.findById(id);
    }
}
