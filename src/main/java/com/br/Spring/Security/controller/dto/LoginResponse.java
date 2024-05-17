package com.br.Spring.Security.controller.dto;

public record LoginResponse(String acessToken, Long expiresIn) {
}
