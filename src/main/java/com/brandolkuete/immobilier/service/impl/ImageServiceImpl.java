package com.brandolkuete.immobilier.service.impl;

import com.brandolkuete.immobilier.entities.Image;
import com.brandolkuete.immobilier.repository.ImageRepository;
import com.brandolkuete.immobilier.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image store(MultipartFile file) throws IOException {
        String imageName= StringUtils.cleanPath(file.getOriginalFilename());
        Image image= new Image(imageName,file.getContentType(),file.getBytes());

        return imageRepository.save(image);
    }

    @Override
    public Image find(String imageId) {
        return imageRepository.getById(imageId);
    }
}
