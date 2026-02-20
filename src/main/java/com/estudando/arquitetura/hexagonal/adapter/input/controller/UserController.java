package com.estudando.arquitetura.hexagonal.adapter.input.controller;

import com.estudando.arquitetura.hexagonal.adapter.input.controller.dto.request.UserRequest;
import com.estudando.arquitetura.hexagonal.adapter.input.controller.dto.response.UserResponse;
import com.estudando.arquitetura.hexagonal.domain.model.User;
import com.estudando.arquitetura.hexagonal.domain.port.in.UserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserUseCase service;

    public UserController(UserUseCase service){
        this.service = service;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest user){
        service.createUser(new User(null, user.name(), user.email()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<User> users = service.getAllUsers();

        List<UserResponse> response = users
                .stream()
                .map(user -> new UserResponse(user.name(), user.email()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id){
        User user = service.getUserById(id);

        UserResponse response = new UserResponse(user.name(), user.email());

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserRequest userRequest){
        service.updateUser(id, new User(null, userRequest.name(), userRequest.email()));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
