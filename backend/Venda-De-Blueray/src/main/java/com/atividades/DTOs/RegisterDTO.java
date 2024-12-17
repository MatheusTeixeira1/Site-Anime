package com.atividades.DTOs;

import com.atividades.models.UserRole;

public record RegisterDTO(String username, String password, UserRole role, String email, String image) {

}
