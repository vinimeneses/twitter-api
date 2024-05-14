package com.br.Spring.Security.repository;

import com.br.Spring.Security.entities.Role;
import com.br.Spring.Security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
