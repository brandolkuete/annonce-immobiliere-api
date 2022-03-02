package com.brandolkuete.immobilier.controller;

import com.brandolkuete.immobilier.entities.Image;
import com.brandolkuete.immobilier.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Image> save_file(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(imageService.store(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> get_image(@PathVariable("id") String imageId){
        Image image= imageService.find(imageId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\"" + image.getName() + "\"")
                .body(image.getData());
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Image> get_image(@PathVariable("id") String imageId){
        Image image= imageService.find(imageId);
        return ResponseEntity.ok()
                .body(image);
    }*/
}
