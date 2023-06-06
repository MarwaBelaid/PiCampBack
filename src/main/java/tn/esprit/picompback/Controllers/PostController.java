package tn.esprit.picompback.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Post;
import tn.esprit.picompback.Services.IPostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private IPostService postService;
    @PostMapping("/add")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @GetMapping("/get/{id}")
    public Optional<Post> getByIdPost(@PathVariable long id) {
        Optional<Post> post = postService.getPostById(id);
        return post;
    }
    @GetMapping("/get/all")
    public  List <Post> getAllPosts() {
        return postService.getAllPosts();

    }
    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return "Post deleted successfuly";
    }
}
