package com.example.powerseva1.service;

import com.example.powerseva1.entity.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> getAllUsers();
}