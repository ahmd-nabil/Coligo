package nabil.coligo.mappers;

import nabil.coligo.dtos.AnnouncementDto;
import nabil.coligo.model.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface AnnouncementMapper {
    @Mapping(target = "createdAt", source = "createdDate")
    AnnouncementDto announcementToAnnouncementDto(Announcement announcement);

    @Mapping(target = "createdDate", source = "createdAt")
    Announcement announcementDtoToAnnouncement(AnnouncementDto announcementDto);
}
