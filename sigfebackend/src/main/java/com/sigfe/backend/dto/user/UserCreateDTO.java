package com.sigfe.backend.dto.user;

public record UserCreateDTO(
        String name,
        String email,
        String password
) {
}
