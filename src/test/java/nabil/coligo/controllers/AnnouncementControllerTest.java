package nabil.coligo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nabil.coligo.model.Announcement;
import nabil.coligo.model.User;
import nabil.coligo.services.AnnouncementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ahmed Nabil
 */
@WebMvcTest(AnnouncementController.class)
class AnnouncementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AnnouncementService announcementService;

    @Autowired
    ObjectMapper objectMapper;
    Announcement announcement;
    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(0L)
                .firstName("Ahmed")
                .lastName("Nabil")
                .username("ahmednabil")
                .password("123")
                .build();
        announcement = Announcement.builder()
                .id(1L)
                .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut.")
                .build();
        user.addAnnouncement(announcement);
    }

    @Test
    void testSaveAnnouncement() throws Exception{
        // given
        given(announcementService.save(any())).willReturn(announcement);
        mockMvc.perform(post("/api/v1/announcements")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(announcement)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}