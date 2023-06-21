package tn.esprit.picompback.Services.CommentServices;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.Commentaire;
import tn.esprit.picompback.Entities.Post;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Commentaire addComment(Commentaire c, long idUser,long idPost) throws IOException;
    List<Commentaire> getCommentByIdPost(long idPost);
}
