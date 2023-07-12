package tn.esprit.picompback.Services.CampService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.picompback.Entities.CentreCamp;
import tn.esprit.picompback.Entities.Image;
import tn.esprit.picompback.Repositories.CampRepos.CentreCampRepository;
import tn.esprit.picompback.Services.CampService.InterfaceService.ICentreCampService;
import tn.esprit.picompback.Utils.FileUploadUtil;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class CentreCampService implements ICentreCampService {

    @Autowired
    CentreCampRepository centreCampRepository ;

    @Autowired
    ServletContext context ;

    public String SupprimerPointVirgule (String ch)
    {
        StringBuilder sb = new StringBuilder(ch);
        if (ch.endsWith(";")) {
            sb.deleteCharAt(ch.length() - 1);
            ch = sb.toString();
        }
        return ch ;
    }
   /* @Override
    public void AjouterCentreCamp(CentreCamp cca, MultipartFile[] multipartFiles) throws IOException
     {
         String Photo ="";
         String fileName="" ;

         for (MultipartFile multipartFile : multipartFiles) {
             fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
             Photo += fileName + ";";
         }
         cca.setPhotos_centre(SupprimerPointVirgule (Photo)) ;
         centreCampRepository.save(cca);
             log.info("tesssssst");
             String uploadDir = "images/camp-photos/" + cca.getId_centre();
         for (MultipartFile multipartFile : multipartFiles) {
              FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         }


/*
         String photoUrls = "";

         for (MultipartFile file : multipartFiles) {
             String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + "_" + file.getOriginalFilename());
             storageService.storeFile(file, fileName);
             photoUrls+=storageService.getFileUrl(fileName));
         }

         cca.setPhotos_centre(photoUrls);
*/

    public Set<Image> uploadImage( MultipartFile[] multipartFiles) throws IOException
    {
        Set<Image> img = new HashSet<>() ;

        for (MultipartFile multipartFile : multipartFiles) {
            Image image = new Image (
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    multipartFile.getBytes()
            ) ;
            img.add(image) ;

        }
        return img ;
    }

    @Override
    public CentreCamp AjouterCentreCamp(CentreCamp cca) {
        /*List<String> fileHandles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            //FileHandle fileHandle = new FileHandle();
            //fileHandle.setFileName(fileName);
            //fileHandle.setCentreCamp(cca);
            fileHandles.add(fileName);

            String uploadDir = "images/camp-photos/" + cca.getId_centre();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
*/
        log.info("Ajout du CentreCamp effectué avec succès.");
        return centreCampRepository.save(cca);
    }


    @Override
    public List<CentreCamp> GetCentreCamps() {
        return centreCampRepository.findAll();
    }

    public byte[] removeBase64Prefix(byte[] picByte) {
        String base64Data = new String(picByte);
        String prefix = "data:image/png;base64,";
        if (base64Data.startsWith(prefix)) {
            return Base64.getDecoder().decode(base64Data.substring(prefix.length()));
        }
        return picByte;
    }

    @Override
    public CentreCamp updateCentreCamp(CentreCamp cca) {
        Set<Image> photos = cca.getPhotos();
        Set<Image> updatedPhotos = new HashSet<>();

        for (Image photo : photos) {
            Image updatedPhoto = new Image();
            updatedPhoto.setPicByte(removeBase64Prefix(photo.getPicByte()));
            updatedPhoto.setType(photo.getType());
            updatedPhoto.setName(photo.getName());
            updatedPhotos.add(updatedPhoto);
        }

        cca.setPhotos(updatedPhotos);
        return centreCampRepository.save(cca);
    }

    @Override
    public CentreCamp GetCentreCamp(long id) {
        return centreCampRepository.findById(id).get();
    }

    @Override
    public void DeleteCentreCamp(long id) {
        centreCampRepository.delete(centreCampRepository.findById(id).get());
    }
}
