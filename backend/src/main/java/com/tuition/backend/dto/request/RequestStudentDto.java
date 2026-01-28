package com.tuition.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestStudentDto {

    @NotBlank(message = "First name is required")
    @Size(min = 4, max = 45, message = "First name must be between 4 and 45 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name can only contain letters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 4, max = 45 , message = "Last name must be between 4 and 45 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name can only contain letters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not valid")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{3,}$",
            message = "Invalid email. Domain must end with at least 3 letters (e.g., .com, .net)")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^07[01245678]\\d{7}$",
            message = "Invalid mobile number. Accepted codes: 070, 071, 072, 074, 075, 076, 077, 078")
    private String mobile;
}