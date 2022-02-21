package com.brandolkuete.immobilier.service;

import com.brandolkuete.immobilier.entities.Announce;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AnnounceService {
    Announce save(MultipartFile file, Announce announce) throws IOException;
    Announce get(Long announceId);
    List<Announce> getAll();
}
