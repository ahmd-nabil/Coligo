package nabil.coligo.services;

import nabil.coligo.dtos.AnnouncementDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface AnnouncementService {
    Page<AnnouncementDto> findAll(Integer pageNumber, Integer pageSize);
    Optional<AnnouncementDto> findById(Long id);
    AnnouncementDto save(AnnouncementDto announcementDto);
    Optional<AnnouncementDto> update(Long id, AnnouncementDto announcementDto);
    boolean deleteById(Long id);
}
