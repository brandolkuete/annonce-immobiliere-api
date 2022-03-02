package com.brandolkuete.immobilier.controller;

import com.brandolkuete.immobilier.dto.AnnounceDto;
import com.brandolkuete.immobilier.dto.ImageDto;
import com.brandolkuete.immobilier.entities.Announce;
import com.brandolkuete.immobilier.service.AnnounceService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/announce")
@CrossOrigin(origins = "http://localhost:3000")
public class AnnounceController {

    private final AnnounceService announceService;

    private final SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

    public AnnounceController(AnnounceService announceService) {
        this.announceService = announceService;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Announce> save_announce(@RequestParam("image")MultipartFile file,
                                                  @RequestParam("title") String title,
                                                  @RequestParam("description") String description) throws IOException {
        Announce announce= Announce.builder()
                .title(title)
                .description(description)
                .publicationDate(new Date())
                .build();

        return ResponseEntity.ok(announceService.save(file,announce));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnnounceDto>> all_announces(){
        List<AnnounceDto> announceDtos= announceService.getAll().map(announce -> {

            String imageDownloadUri= ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/image/")
                    .path(announce.getImage().getId())
                    .toUriString();

            ImageDto imageDto= new ImageDto(
                    announce.getImage().getName(),
                    announce.getImage().getType(),
                    imageDownloadUri
            );

            return new AnnounceDto(
                    announce.getId(),
                    announce.getTitle(),
                    announce.getDescription(),
                    announce.getPublicationDate(),
                    imageDto
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(announceDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnounceDto> get_announce(@PathVariable("id") Long announceId){
        Announce announce= announceService.get(announceId);

        String imageDownloadUri= ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/image/")
                .path(announce.getImage().getId())
                .toUriString();

        ImageDto imageDto= new ImageDto(
                announce.getImage().getName(),
                announce.getImage().getType(),
                imageDownloadUri
        );

        AnnounceDto announceDto= new AnnounceDto(
                announce.getId(),
                announce.getTitle(),
                announce.getDescription(),
                announce.getPublicationDate(),
                imageDto
        );
        return ResponseEntity.ok(announceDto);
    }
}
