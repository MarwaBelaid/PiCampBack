package tn.esprit.picompback.Services;

import tn.esprit.picompback.Entities.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    Post addPost(Post post);
    Optional<Post> getPostById(long id);
    List<Post> getAllPosts();
    void deletePost(long id);
}
