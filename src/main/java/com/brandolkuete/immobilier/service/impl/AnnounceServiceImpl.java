package com.brandolkuete.immobilier.service.impl;

import com.brandolkuete.immobilier.entities.Announce;
import com.brandolkuete.immobilier.entities.Image;
import com.brandolkuete.immobilier.repository.AnnounceRepository;
import com.brandolkuete.immobilier.service.AnnounceService;
import com.brandolkuete.immobilier.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AnnounceServiceImpl implements AnnounceService {

    private final AnnounceRepository announceRepository;
    private final ImageService imageService;

    public AnnounceServiceImpl(AnnounceRepository announceRepository, ImageService imageService) {
        this.announceRepository = announceRepository;
        this.imageService = imageService;
    }

    @Override
    public Announce save(MultipartFile file, Announce announce) throws IOException {
        Image image= imageService.store(file);
        announce.setImage(image);

        return announceRepository.save(announce);
    }

    @Override
    public Announce get(Long announceId) {
        return announceRepository.getById(announceId);
    }

    @Override
    public List<Announce> getAll() {
        return announceRepository.findAll();
    }
}
