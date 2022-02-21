package com.brandolkuete.immobilier.controller;

import com.brandolkuete.immobilier.entities.Announce;
import com.brandolkuete.immobilier.service.AnnounceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/announce")
public class AnnounceController {

    private final AnnounceService announceService;

    public AnnounceController(AnnounceService announceService) {
        this.announceService = announceService;
    }

    @PostMapping("/save")
    public ResponseEntity<Announce> save_announce(@RequestParam("file")MultipartFile file, @RequestBody Announce announce) throws IOException {
        return ResponseEntity.ok(announceService.save(file,announce));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Announce>> all_announces(){
        return ResponseEntity.ok(announceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announce> get_announce(@PathVariable("id") Long announceId){
        return ResponseEntity.ok(announceService.get(announceId));
    }
}
