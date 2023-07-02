package tn.esprit.picompback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.picompback.Entities.Enumeration.role_enum;
import tn.esprit.picompback.Entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(role_enum name);
}
