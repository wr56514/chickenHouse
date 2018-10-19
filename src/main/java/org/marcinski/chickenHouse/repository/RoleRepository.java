package org.marcinski.chickenHouse.repository;

import org.marcinski.chickenHouse.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
