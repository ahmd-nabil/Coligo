package nabil.coligo.controllers;

import lombok.RequiredArgsConstructor;
import nabil.coligo.dtos.AnnouncementDto;
import nabil.coligo.exceptions.AnnouncementNotFoundException;
import nabil.coligo.services.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * @author Ahmed Nabil
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("${controller.announcement.path}")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public Page<AnnouncementDto> findAll(@RequestParam(required = false) Integer pageNumber,
                                      @RequestParam(required = false) Integer pageSize) {
        return announcementService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> findById(@PathVariable(name = "id") Long id) {
        Optional<AnnouncementDto> announcementDtoOptional = announcementService.findById(id);
        if(announcementDtoOptional.isEmpty())
            throw new AnnouncementNotFoundException();
        return ResponseEntity.ok(announcementDtoOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> saveAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        AnnouncementDto saved = announcementService.save(announcementDto);
        return ResponseEntity.created(URI.create("/api/v1/announcements/"+saved.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable(name = "id") Long id,
                                                @RequestBody AnnouncementDto announcementDto) {
        announcementService.update(id, announcementDto).orElseThrow(AnnouncementNotFoundException::new);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable(name = "id") Long id) {
        boolean deleted = announcementService.deleteById(id);
        if(deleted)
            return ResponseEntity.ok().build();
        throw new AnnouncementNotFoundException();
    }
}
