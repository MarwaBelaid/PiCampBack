package tn.esprit.picompback.Repositories.CommentRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.Commentaire;
import tn.esprit.picompback.Entities.Post;
@Repository
public interface ICommentRepository extends JpaRepository<Commentaire,Long> {
}
