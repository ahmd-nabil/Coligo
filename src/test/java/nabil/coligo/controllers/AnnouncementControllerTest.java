package nabil.coligo.controllers;

import nabil.coligo.services.auth.JwtService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

/**
 * @author Ahmed Nabil
 */
@WebMvcTest(AnnouncementController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(JwtService.class)
class AnnouncementControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    AnnouncementService announcementService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//    Announcement announcement;
//
//    private static String token;
//
//    @BeforeAll
//    static void beforeAll() {
//        User user = User.builder()
//                .id(0L)
//                .firstName("Ahmed")
//                .lastName("Nabil")
//                .email("ahmednabil@gmail.com")
//                .password("12345")
//                .role(Role.INSTRUCTOR)
//                .build();
//        token = "Bearer "+new JwtService().generateToken(user);
//    }
//
//    @BeforeEach
//    void setUp() {
//        User user = User.builder()
//                .id(0L)
//                .firstName("Ahmed")
//                .lastName("Nabil")
//                .email("ahmednabil@gmail.com")
//                .password("12345")
//                .role(Role.INSTRUCTOR)
//                .build();
//        announcement = Announcement.builder()
//                .id(1L)
//                .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut.")
//                .build();
//        user.addAnnouncement(announcement);
//    }
//
//    @Test
//    void testSaveAnnouncement() throws Exception{
//        // given
//        given(announcementService.save(any())).willReturn(announcement);
//        mockMvc.perform(
//                post("/api/v1/announcements")
//                .header("Authorization", token)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(announcement)))
//                .andExpect(status().isCreated())
//                .andExpect(header().exists("Location"));
//    }
}