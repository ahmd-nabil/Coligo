package nabil.coligo.services;

import nabil.coligo.dtos.AnnouncementAllDto;
import nabil.coligo.dtos.AnnouncementCreateDto;
import nabil.coligo.dtos.AnnouncementDto;
import org.springframework.data.domain.Page;

/**
 * @author Ahmed Nabil
 */
public interface AnnouncementService {
    Page<AnnouncementAllDto> findAll(Integer pageNumber, Integer pageSize);
    AnnouncementDto findById(Long id);
    AnnouncementDto save(AnnouncementCreateDto announcementCreateDto);
    AnnouncementDto update(Long id, AnnouncementCreateDto announcementCreateDto);
    void deleteById(Long id);
}
