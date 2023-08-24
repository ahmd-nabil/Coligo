package nabil.coligo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed Nabil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "not valid email")
    @NotNull(message = "email can't be null")
    private String email;

    @NotEmpty(message = "password can't be empty")
    @NotNull(message = "password can't be null")
    @Size(min = 5, max = 50, message = "password must be between 5 and 50 characters")
    private String password;
}
