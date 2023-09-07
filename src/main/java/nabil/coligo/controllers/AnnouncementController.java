package nabil.coligo.controllers;

import lombok.RequiredArgsConstructor;
import nabil.coligo.dtos.AnnouncementAllDto;
import nabil.coligo.dtos.AnnouncementCreateDto;
import nabil.coligo.dtos.AnnouncementDto;
import nabil.coligo.dtos.AnnouncementUpdateDto;
import nabil.coligo.services.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author Ahmed Nabil
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public Page<AnnouncementAllDto> findAll(@RequestParam(required = false) Integer pageNumber,
                                            @RequestParam(required = false) Integer pageSize) {
        return announcementService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> findById(@PathVariable(name = "id") Long id) {
        AnnouncementDto announcementDto = announcementService.findById(id);
        return ResponseEntity.ok(announcementDto);
    }

    @PostMapping
    public ResponseEntity<?> saveAnnouncement(@RequestBody AnnouncementCreateDto announcementCreateDto) {
        AnnouncementDto saved = announcementService.save(announcementCreateDto);
        return ResponseEntity.created(URI.create("/api/v1/announcements/" + saved.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable(name = "id") Long id,
                                                @RequestBody AnnouncementUpdateDto dto) {
        announcementService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable(name = "id") Long id) {
        announcementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
