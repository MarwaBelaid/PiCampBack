package tn.esprit.picompback.Services.PostServices;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Entities.Commande;
import tn.esprit.picompback.Entities.Equipement;
import tn.esprit.picompback.Entities.Post;
import tn.esprit.picompback.Repositories.PostRepos.IPostRepository;
import tn.esprit.picompback.Utils.FileUploadUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepo;
    @Override
    public Post addPost(Post p, MultipartFile multipartFile) throws IOException {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            p.setImagePath(fileName);
            Post savedPost =postRepo.save(p);
            String uploadDir = "images/posts/" + p.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            return savedPost;

    }
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
    @Override
    public void updateLike(long id,boolean liked,long likes){
        Post p = postRepo.findById(id).orElse(null);
        p.setLike(liked);
        p.setNbLike(likes);
        postRepo.save(p);
    }
}
