package tn.esprit.picompback.Repositories.PostRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.Commentaire;
import tn.esprit.picompback.Entities.Post;

import java.io.IOException;
import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {


}
