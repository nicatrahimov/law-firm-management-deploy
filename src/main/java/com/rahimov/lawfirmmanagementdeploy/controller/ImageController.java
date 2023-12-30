package com.rahimov.lawfirmmanagementdeploy.controller;



import com.rahimov.lawfirmmanagementdeploy.service.ImageService;
import com.rahimov.lawfirmmanagementdeploy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

  @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadPhoto(@RequestParam("photo") MultipartFile photo,
                                              @PathVariable("id") Long id) throws IOException {
      return imageService.uploadImage(photo,id);
  }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable("id") Long id) {
        return imageService.downloadImage(id);
    }

}