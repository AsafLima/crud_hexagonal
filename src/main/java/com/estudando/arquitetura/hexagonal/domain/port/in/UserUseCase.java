package com.estudando.arquitetura.hexagonal.domain.port.in;

import com.estudando.arquitetura.hexagonal.domain.model.User;

import java.util.List;

public interface UserUseCase {

    User getUserById(Long id);
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);

}
