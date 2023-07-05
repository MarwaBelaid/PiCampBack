package tn.esprit.picompback.Repositories.UserRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.picompback.Entities.Utilisateurs;

public interface UtilisateurRepository extends JpaRepository<Utilisateurs,Long> {
}
