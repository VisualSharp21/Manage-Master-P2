package com.sigfe.backend.dto.user;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {
}
