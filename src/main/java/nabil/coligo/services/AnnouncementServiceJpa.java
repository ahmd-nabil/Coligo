package nabil.coligo.services;

import lombok.RequiredArgsConstructor;
import nabil.coligo.dtos.AnnouncementAllDto;
import nabil.coligo.dtos.AnnouncementCreateDto;
import nabil.coligo.dtos.AnnouncementDto;
import nabil.coligo.exceptions.AnnouncementNotFoundException;
import nabil.coligo.exceptions.UserNotFoundException;
import nabil.coligo.mappers.AnnouncementMapper;
import nabil.coligo.model.Announcement;
import nabil.coligo.model.User;
import nabil.coligo.repositories.AnnouncementRepository;
import nabil.coligo.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceJpa implements AnnouncementService{

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final UserRepository userRepository;

    @Override
    public Page<AnnouncementAllDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PagingService.buildPageRequest(pageNumber, pageSize);
        return announcementRepository
                .findAllByOrderByCreatedAtDesc(pageable)
                .map(announcementMapper::toAnnouncementAllDto);
    }

    @Override
    public AnnouncementDto findById(Long id) {
        return announcementRepository.findById(id)
                .map(announcementMapper::toAnnouncementDto).orElseThrow(AnnouncementNotFoundException::new);
    }

    @Override
    public AnnouncementDto save(AnnouncementCreateDto announcementCreateDto) {
        User user = userRepository.findById(announcementCreateDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Announcement announcement = announcementMapper.toAnnouncement(announcementCreateDto);
        user.addAnnouncement(announcement);
        return announcementMapper.toAnnouncementDto(announcement);
    }

    // TODO needs to be checked
    @Override
    public AnnouncementDto update(Long id, AnnouncementCreateDto announcementCreateDto) {
        Announcement updatedAnnouncement = announcementRepository.findById(id).orElseThrow(AnnouncementNotFoundException::new);
        updatedAnnouncement.setContent(announcementCreateDto.getContent());
        return announcementMapper.toAnnouncementDto(updatedAnnouncement);
    }

    @Override
    public void deleteById(Long id) {
        if(!announcementRepository.existsById(id)) throw new AnnouncementNotFoundException();
        announcementRepository.deleteById(id);
    }

}
