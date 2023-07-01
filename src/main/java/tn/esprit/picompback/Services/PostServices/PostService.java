package tn.esprit.picompback.Services.PostServices;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.*;
import tn.esprit.picompback.Repositories.PostRepos.IPostRepository;
import tn.esprit.picompback.Repositories.UserRepository;
import tn.esprit.picompback.Utils.FileUploadUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepo;
    private UserRepository userRepo;
    @Override
    public Post addPost(Post p, MultipartFile multipartFile,long idUser) throws IOException {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            p.setImagePath(fileName);
            Utilisateurs user = userRepo.findById(idUser).get();
            p.setPost_utilisateurs(user);
            Post savedPost =postRepo.save(p);
            String uploadDir = "images/posts/" + p.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);



            return postRepo.save(p);

    }
    @Override
    public Optional<Post> getPostById (long id){
        return postRepo.findById(id);
    }
    @Override()
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
