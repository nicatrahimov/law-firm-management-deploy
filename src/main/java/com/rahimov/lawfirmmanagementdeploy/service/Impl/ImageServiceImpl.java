package com.rahimov.lawfirmmanagementdeploy.service.Impl;


import com.rahimov.lawfirmmanagementdeploy.exception.ImageDownloadFailed;
import com.rahimov.lawfirmmanagementdeploy.model.Image;
import com.rahimov.lawfirmmanagementdeploy.model.User;
import com.rahimov.lawfirmmanagementdeploy.repository.ImageRepository;
import com.rahimov.lawfirmmanagementdeploy.repository.UserRepository;
import com.rahimov.lawfirmmanagementdeploy.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {


    public static String DIRECTORY="/home/nicat/Desktop/law-firm-management/src/main/resources/profile photos/";

private final ImageRepository imageRepository;
private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> uploadImage(MultipartFile file,Long id) throws IOException {
        if (file.isEmpty()) {
            throw new FileNotFoundException();
        }

        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));

        Image image = new Image();
        String fileName = saveToFileSys(file);

        if (user.getImage() == null) {
            image.setName(fileName);
            imageRepository.save(image);
            user.setImage(image);
            userRepository.save(user);
        }else {
            String imageName = user.getImage().getName();
            Image byName = imageRepository.findByName(imageName);
            byName.setName(fileName);
            imageRepository.save(byName);
        }
        return new ResponseEntity<>("good",HttpStatusCode.valueOf(200));
    }

    private String saveToFileSys(MultipartFile file) throws IOException {
        // Generate a unique file name (you may use your own logic)
        String fileName =  file.getOriginalFilename() + "_" + System.currentTimeMillis();

        // Build the file path
        Path filePath = Paths.get(DIRECTORY).resolve(fileName);

        // Save the file to the specified directory
        file.transferTo(filePath.toFile());

//            // Build the download URL for the client
//            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/downloadFile/")
//                    .path(fileName)
//                    .toUriString();
        return fileName;

    }

    @Override
    public ResponseEntity<byte[]> downloadImage(Long id) {

        User user = userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("user not found"));

        Image userImage = user.getImage();

        try{
            Path imagePath = Paths.get(DIRECTORY).resolve(userImage.getName());

            // Read the image bytes
            byte[] imageBytes = Files.readAllBytes(imagePath);

            // Set the content type and headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image format
            headers.setContentDispositionFormData("inline", userImage.getName());

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }catch (IOException e){
            throw new ImageDownloadFailed();
        }

        }

    @Override
    public Image saveImage(Image image) {
        Image image1 = imageRepository.save(image);
        return image1;
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image getById(Long id) {
       return imageRepository.findById(id).orElseThrow(()->new RuntimeException("Image not found"));
    }


}
