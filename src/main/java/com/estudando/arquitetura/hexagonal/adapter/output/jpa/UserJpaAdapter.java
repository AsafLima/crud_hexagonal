package com.estudando.arquitetura.hexagonal.adapter.output.jpa;

import com.estudando.arquitetura.hexagonal.domain.model.User;
import com.estudando.arquitetura.hexagonal.domain.port.out.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = userJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    private UserJpaEntity toEntity(User user){
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.id());
        entity.setNome(user.name());
        entity.setEmail(user.email());
        return entity;
    }

    private User toDomain(UserJpaEntity entity){
        return new User(
                entity.getId(),
                entity.getNome(),
                entity.getEmail()
        );
    }
}
