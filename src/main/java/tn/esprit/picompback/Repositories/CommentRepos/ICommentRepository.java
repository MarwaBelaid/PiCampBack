package tn.esprit.picompback.Repositories.CommentRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.Commentaire;
import tn.esprit.picompback.Entities.Post;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Commentaire,Long> {
    @Query("SELECT c FROM Commentaire c WHERE c.post_coment.id = :idPost")

    List<Commentaire> findByPostComentId(@Param("idPost") long idPost);

}
