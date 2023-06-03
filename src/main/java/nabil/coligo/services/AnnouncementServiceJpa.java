package nabil.coligo.services;

import lombok.RequiredArgsConstructor;
import nabil.coligo.model.Announcement;
import nabil.coligo.repositories.AnnouncementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
public class AnnouncementServiceJpa implements AnnouncementService{

    private final AnnouncementRepository announcementRepository;
    @Override
    public Page<Announcement> findAll(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PagingService.buildPageRequest(pageNumber, pageSize);
        return announcementRepository.findAllByOrderByCreatedDateDesc(pageRequest);
    }

    @Override
    public Optional<Announcement> findById(Long id) {
        return announcementRepository.findById(id);
    }

    @Override
    public Announcement save(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    @Transactional
    public Optional<Announcement> update(Long id, Announcement announcement) {
        Optional<Announcement> announcementOptional = announcementRepository.findById(id);
        if(announcementOptional.isEmpty()) {
            return announcementOptional;
        }
        Announcement foundAnnouncement = announcementOptional.get();
        foundAnnouncement.setContent(announcement.getContent());
        return Optional.of(foundAnnouncement);
    }

    @Override
    public boolean deleteById(Long id) {
        if(announcementRepository.existsById(id)) {
            announcementRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
