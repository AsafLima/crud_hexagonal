package com.estudando.arquitetura.hexagonal.adapter.input.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        @Email
        String email
) {
}
