package nabil.coligo.services;

import lombok.RequiredArgsConstructor;
import nabil.coligo.dtos.AnnouncementDto;
import nabil.coligo.mappers.AnnouncementMapper;
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
    private final AnnouncementMapper announcementMapper;
    @Override
    public Page<AnnouncementDto> findAll(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PagingService.buildPageRequest(pageNumber, pageSize);
        return announcementRepository
                .findAllByOrderByCreatedDateDesc(pageRequest)
                .map(announcementMapper::announcementToAnnouncementDto);
    }

    @Override
    public Optional<AnnouncementDto> findById(Long id) {
        return announcementRepository.findById(id).map(announcementMapper::announcementToAnnouncementDto);
    }

    @Override
    public AnnouncementDto save(AnnouncementDto announcementDto) {
        return announcementMapper.announcementToAnnouncementDto(
                announcementRepository.save(announcementMapper.announcementDtoToAnnouncement(announcementDto))
        );
    }

    @Override
    @Transactional
    public Optional<AnnouncementDto> update(Long id, AnnouncementDto announcementDto) {
        Optional<AnnouncementDto> dtoOptional = announcementRepository.findById(id).map(announcementMapper::announcementToAnnouncementDto);
        if(dtoOptional.isEmpty()) {
            return dtoOptional;
        }
        AnnouncementDto foundDto = dtoOptional.get();
        foundDto.setContent(foundDto.getContent());
        return Optional.of(foundDto);
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
