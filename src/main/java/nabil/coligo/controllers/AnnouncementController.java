package nabil.coligo.controllers;

import lombok.RequiredArgsConstructor;
import nabil.coligo.exceptions.AnnouncementNotFoundException;
import nabil.coligo.model.Announcement;
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
@RequestMapping("/api/v1/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public Page<Announcement> findAll(@RequestParam(required = false) Integer pageNumber,
                                      @RequestParam(required = false) Integer pageSize) {
        return announcementService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable(name = "id") Long id) {
        Optional<Announcement> announcementOptional = announcementService.findById(id);
        if(announcementOptional.isEmpty())
            throw new AnnouncementNotFoundException();
        return ResponseEntity.ok(announcementOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> saveAnnouncement(@RequestBody Announcement announcement) {
        Announcement saved = announcementService.save(announcement);
        return ResponseEntity.created(URI.create("/api/v1/announcements/"+saved.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable(name = "id") Long id,
                                                @RequestBody Announcement announcement) {
        announcementService.update(id, announcement).orElseThrow(AnnouncementNotFoundException::new);
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
