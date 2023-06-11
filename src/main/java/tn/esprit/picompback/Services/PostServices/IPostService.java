package tn.esprit.picompback.Services.PostServices;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Entities.Post;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IPostService {
    Post addPost(Post p, MultipartFile multipartFile) throws IOException;
    Optional<Post> getPostById(long id);
    List<Post> getAllPosts();
    void deletePost(long id);
    void updateLike(long id,boolean liked,long likes);
}
