package com.brandolkuete.immobilier.service;

import com.brandolkuete.immobilier.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image store(MultipartFile file) throws IOException;
}
