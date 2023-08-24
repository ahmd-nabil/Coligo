package nabil.coligo.mappers;

import nabil.coligo.dtos.AnnouncementAllDto;
import nabil.coligo.dtos.AnnouncementCreateDto;
import nabil.coligo.dtos.AnnouncementDto;
import nabil.coligo.model.Announcement;
import org.mapstruct.Mapper;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface AnnouncementMapper {
    AnnouncementAllDto toAnnouncementAllDto(Announcement announcement);
    Announcement toAnnouncement(AnnouncementCreateDto announcementCreateDto);
    AnnouncementDto toAnnouncementDto(Announcement announcement);
}
