package nabil.coligo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCreateDto {
    private String courseName;
    private String topic;
    private LocalDateTime dueTo;
    private Set<QuestionCreateDto> questions = new HashSet<>();
}
