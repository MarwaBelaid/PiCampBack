package tn.esprit.picompback.Controllers.PostController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Enumeration.TypeCommande;
import tn.esprit.picompback.Entities.Enumeration.TypePaiement;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Entities.Post;
import tn.esprit.picompback.Repositories.PostRepos.IPostRepository;
import tn.esprit.picompback.Services.PostServices.IPostService;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Services.PostServices.PostService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final String IMAGE_UPLOAD_DIR = "images/";
    @Autowired
    private IPostService postService;
    @PostMapping(value = "/create/{idUser}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Post createPost(@RequestHeader("Content-Type") String HeaderValue, @ModelAttribute Post p, @RequestParam("image_path") MultipartFile multipartFile,@PathVariable ("idUser") long idUser) throws IOException {
//        long idUser = Long.parseLong(idUser);
        return postService.addPost(p,multipartFile,idUser);
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
    @PutMapping("/like/{id}")
    public String updateLike(@PathVariable Long id, @RequestParam boolean liked,@RequestBody long likes) {
        postService.updateLike(id,liked,likes);
        return "like mis a jour";
    }
}
