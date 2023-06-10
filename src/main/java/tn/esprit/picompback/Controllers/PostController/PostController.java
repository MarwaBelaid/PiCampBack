package tn.esprit.picompback.Controllers.PostController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.picompback.Entities.Post;
import tn.esprit.picompback.Repositories.PostRepos.IPostRepository;
import tn.esprit.picompback.Services.PostServices.IPostService;
import org.springframework.web.multipart.MultipartFile;
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
//    @PostMapping("/adaad")
//    public Post addPost(@RequestBody Post post) {
//        return postService.addPost(post);
//    }
//    @PostMapping("/add/data")
//    public ResponseEntity<String> createPost(@RequestParam("sujet") String sujet,
//                                             @RequestParam("contenu") String contenu,
//                                             @RequestParam("picture") MultipartFile picture) {
//        try {
//            byte[] pictureBytes = picture.getBytes();
//            Post post = postService.createPost(sujet, contenu, pictureBytes);
//            // Retourner une réponse indiquant que le post a été créé avec succès
//            return ResponseEntity.ok("Post créé avec succès.");
//        } catch (IOException e) {
//            // Gérer l'erreur si la récupération des données de l'image échoue
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création du post.");
//        }
//    }



    @Autowired
    private IPostRepository postRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@ModelAttribute Post post, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            // Enregistrer le post dans la base de données
            Post createdPost = postRepository.save(post);

            // Vérifier si un fichier d'image a été téléchargé
            if (imageFile != null && !imageFile.isEmpty()) {
                // Déplacer le fichier d'image vers le dossier de téléchargement
                String fileName = imageFile.getOriginalFilename();
                Path destination = Paths.get(IMAGE_UPLOAD_DIR + fileName);
                Files.copy(imageFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

                // Mettre à jour le post avec le chemin de l'image
                String imagePath = IMAGE_UPLOAD_DIR + fileName;
                createdPost.setImagePath(imagePath);
                postRepository.save(createdPost);
            }

            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error uploading image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
