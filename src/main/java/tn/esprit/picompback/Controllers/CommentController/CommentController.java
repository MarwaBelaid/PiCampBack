package tn.esprit.picompback.Controllers.CommentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.Commentaire;
import tn.esprit.picompback.Entities.Post;
import tn.esprit.picompback.Services.CommentServices.ICommentService;
import tn.esprit.picompback.Services.PostServices.IPostService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @PostMapping(value = "/create/{idUser}/{idPost}")
    public Commentaire addComment(@RequestBody Commentaire c, @PathVariable ("idUser") long idUser,@PathVariable ("idPost") long idPost) throws IOException {
        return commentService.addComment(c,idUser,idPost);
    }
    @GetMapping("/get/all/{idPost}")
    public List<Commentaire> getAllCommentByPost(@PathVariable ("idPost") long idPost) {
        return commentService.getCommentByIdPost(idPost);

    }
}
