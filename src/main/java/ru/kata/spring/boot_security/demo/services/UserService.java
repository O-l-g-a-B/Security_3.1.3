package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public void saveUser(User user);

    public void deleteUser(Long id);

    public void updateUser(long id, User user);

    public User findByUsername(String username);

    public User findById(long id);

    public List<User> getAllUsers();


}
