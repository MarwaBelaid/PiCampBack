package tn.esprit.picompback.Services.PostServices;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.picompback.Entities.Post;
import tn.esprit.picompback.Repositories.PostRepos.IPostRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepo;
//    @Override
//    public Post addPost (Post post){
//        return postRepo.save(post);
//    }
//    public Post createPost(String sujet, String contenu, byte[] picture) {
//        Post post = new Post();
//        post.setSujetPost(sujet);
//        post.setContenuPost(contenu);
//        post.setPicture(picture);
//        // Enregistrer le post dans la base de données
//        return postRepo.save(post);
//    }
    @Override
    public Optional<Post> getPostById (long id){
        return postRepo.findById(id);
    }
    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }
    @Override
    public void deletePost(long id) {
        postRepo.deleteById(id);
    }
}
