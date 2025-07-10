package br.com.nutriplanner.user_service.dto;

import java.util.Set;

public record UserRegistrationDTO(String name, String password, Set<String> preferences) {}