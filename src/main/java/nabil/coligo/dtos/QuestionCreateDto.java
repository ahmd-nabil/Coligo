package nabil.coligo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Ahmed Nabil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionCreateDto {
    private String question;
    private Set<AnswerCreateDto> answers = new HashSet<>();
}
