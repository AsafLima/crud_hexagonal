package com.estudando.arquitetura.hexagonal.adapter.output.memory;

import com.estudando.arquitetura.hexagonal.domain.model.User;
import com.estudando.arquitetura.hexagonal.domain.port.out.UserRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepositoryMemory implements UserRepository {

    private final Map<Long, User> database = new HashMap<>();
    private Long idSequence = 1L;

    @Override
    public User save(User user) {
        if (user.id() == null){
            Long id = idSequence++;
            User userWithId = new User(id, user.name(), user.email());
            database.put(id, userWithId);
            return userWithId;
        } else {
            database.put(user.id(), user);
            return user;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }
}
