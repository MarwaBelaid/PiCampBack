package tn.esprit.picompback.Repositories;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.picompback.Entities.Utilisateurs;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilisateurs, Long> {
    Optional<Utilisateurs> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
