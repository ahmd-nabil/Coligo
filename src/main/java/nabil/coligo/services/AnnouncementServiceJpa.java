package nabil.coligo.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nabil.coligo.exceptions.AnnouncementNotFoundException;
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
@Data
@RequiredArgsConstructor
public class AnnouncementServiceJpa implements AnnouncementService{

    private final AnnouncementRepository announcementRepository;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;
    @Override
    public Page<Announcement> findAll(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        return announcementRepository.findAllByOrderByCreatedDateDesc(pageRequest);
    }

    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        Integer queryPageNumber = DEFAULT_PAGE_NUMBER;
        Integer queryPageSize = DEFAULT_PAGE_SIZE;
        if(pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        }

        if(pageSize != null && pageSize > 0) {
            queryPageSize = pageSize;
        }
        return PageRequest.of(queryPageNumber, queryPageSize);
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
        Announcement foundAnnouncement = announcementRepository.findById(id).orElseThrow(AnnouncementNotFoundException::new);
        foundAnnouncement.setDescription(announcement.getDescription());
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
