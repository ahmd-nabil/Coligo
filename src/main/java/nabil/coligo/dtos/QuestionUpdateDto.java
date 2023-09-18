package nabil.coligo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * @author Ahmed Nabil
 */
public class QuestionUpdateDto {
    private Long id;
    private String question;
    @Builder.Default
    private Set<AnswerUpdateDto> answers = new HashSet<>();
}
