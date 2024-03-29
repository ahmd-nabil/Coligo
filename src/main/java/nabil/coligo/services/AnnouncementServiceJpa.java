package nabil.coligo.services;

import lombok.RequiredArgsConstructor;
import nabil.coligo.dtos.AnnouncementAllDto;
import nabil.coligo.dtos.AnnouncementCreateDto;
import nabil.coligo.dtos.AnnouncementDto;
import nabil.coligo.dtos.AnnouncementUpdateDto;
import nabil.coligo.exceptions.AnnouncementNotFoundException;
import nabil.coligo.mappers.AnnouncementMapper;
import nabil.coligo.model.Announcement;
import nabil.coligo.model.User;
import nabil.coligo.repositories.AnnouncementRepository;
import nabil.coligo.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceJpa implements AnnouncementService{
    // TODO validate that only the owner of the resource would be able to change it
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final UserRepository userRepository;
    private final UtilService utilService;
    @Override
    public Page<AnnouncementAllDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PagingService.getPageable(pageNumber, pageSize);
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
        User user = userRepository.findByEmailIgnoreCase(SecurityContextHolder.getContext().getAuthentication().getName());
        Announcement announcement = announcementMapper.toAnnouncement(announcementCreateDto);
        user.addAnnouncement(announcement);
        return announcementMapper.toAnnouncementDto(announcement);
    }

    // TODO ask for best practices about securing against non-resource-owners
    @Override
//    @PostAuthorize("returnObject.user.email == authentication.name") // this will work only with get requests (does not roll back)
    public AnnouncementDto update(Long id, AnnouncementUpdateDto dto) {
        Announcement updatedAnnouncement = announcementRepository.findById(id).orElseThrow(AnnouncementNotFoundException::new);
        String resourceOwnerEmail = updatedAnnouncement.getUser().getUsername();
        utilService.checkIfEqualsAuthenticatedUser(resourceOwnerEmail);
        updatedAnnouncement.setContent(dto.getContent());
        return announcementMapper.toAnnouncementDto(updatedAnnouncement);
    }

    @Override
    public void deleteById(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(AnnouncementNotFoundException::new);
        utilService.checkIfEqualsAuthenticatedUser(announcement.getUser().getEmail());
        announcementRepository.deleteById(id);
    }
}
