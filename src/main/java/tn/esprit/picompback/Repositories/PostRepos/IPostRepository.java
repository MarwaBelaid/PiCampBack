package tn.esprit.picompback.Repositories.PostRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.Post;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
}
