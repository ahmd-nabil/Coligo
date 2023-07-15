package nabil.coligo.model.auth;

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
public class RegisterRequest {
    @Email(message = "not valid email")
    @NotNull(message = "email can't be null")
    private String email;

    @NotEmpty(message = "first name can't be empty")
    @NotNull(message = "first name can't be empty")
    @Size(min = 3, max = 50, message = "first name must be between 3 and 50 characters")
    private String firstName;


    @NotEmpty(message = "last name can't be empty")
    @NotNull(message = "last name can't be empty")
    @Size(min = 3, max = 50, message = "last name must be between 3 and 50 characters")
    private String lastName;

    @NotEmpty(message = "password can't be empty")
    @NotNull(message = "password can't be empty")
    @Size(min = 5, max = 50, message = "password must be between 5 and 50 characters")
    private String password;
}
