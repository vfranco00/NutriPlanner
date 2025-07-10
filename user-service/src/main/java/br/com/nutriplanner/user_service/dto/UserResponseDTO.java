package br.com.nutriplanner.user_service.dto;

import java.util.Set;

public record UserResponseDTO(Long id, String name, Set<String> preferences) {}