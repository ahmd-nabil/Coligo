package nabil.coligo.services;

import nabil.coligo.model.Announcement;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface AnnouncementService {
    Page<Announcement> findAll(Integer pageNumber, Integer pageSize);
    Optional<Announcement> findById(Long id);
    Announcement save(Announcement announcement);
    Optional<Announcement> update(Long id, Announcement announcement);
    boolean deleteById(Long id);
}
