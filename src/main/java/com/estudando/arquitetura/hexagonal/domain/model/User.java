package com.estudando.arquitetura.hexagonal.domain.model;

public record User(
        Long id,
        String name,
        String email
) {
}