package tn.esprit.picompback.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.*;
@Getter
@Setter
@AllArgsConstructor
public class SignupRequest implements Serializable {
    @NotBlank

    private String username;

    @NotBlank

    @Email
    private String email;

    private Set<String> role;

    @NotBlank

    private String password;


}
