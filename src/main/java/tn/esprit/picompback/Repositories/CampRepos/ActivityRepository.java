package tn.esprit.picompback.Repositories.CampRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
}
