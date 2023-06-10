package tn.esprit.picompback.Services.PostServices;

import tn.esprit.picompback.Entities.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
//    Post addPost(Post post);
//    Post createPost(String sujet, String contenu, byte[] picture);
    Optional<Post> getPostById(long id);
    List<Post> getAllPosts();
    void deletePost(long id);
}
