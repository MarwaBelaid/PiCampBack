package tn.esprit.picompback.Services.CommentServices;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.Commentaire;
import tn.esprit.picompback.Entities.Post;
import tn.esprit.picompback.Entities.Utilisateurs;
import tn.esprit.picompback.Repositories.CommentRepos.ICommentRepository;
import tn.esprit.picompback.Repositories.PostRepos.IPostRepository;
import tn.esprit.picompback.Repositories.UserRepos.UtilisateurRepository;
import tn.esprit.picompback.Services.PostServices.IPostService;
import tn.esprit.picompback.Utils.FileUploadUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;
    private UtilisateurRepository userRepo;
    private IPostRepository postRepo;
    @Override
    public Commentaire addComment(Commentaire c, long idUser,long idPost) throws IOException {

            Commentaire savedComment =commentRepository.save(c);
            Utilisateurs user = userRepo.findById(idUser).get();
            Post post = postRepo.findById(idPost).get();
            savedComment.setComent_utilisateurs(user);
            savedComment.setPost_coment(post);
            commentRepository.save(savedComment);

            return savedComment;

    }

}
