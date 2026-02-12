package com.estudando.arquitetura.hexagonal.domain.service;

import com.estudando.arquitetura.hexagonal.domain.model.User;
import com.estudando.arquitetura.hexagonal.domain.port.in.UserUseCase;
import com.estudando.arquitetura.hexagonal.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User getUserById(Long id){
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @Override
    public void createUser(User user){
        repository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User updated = new User(id, user.name(), user.email());
        repository.save(updated);
    }

    @Override
    public void deleteUser(Long id){
        repository.deleteById(id);
    }

}

