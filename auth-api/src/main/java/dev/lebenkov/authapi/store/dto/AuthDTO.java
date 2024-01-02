package dev.lebenkov.authapi.store.dto;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthDTO {
    @Size(min = 3, max = 20, message = "Username should be 3 - 20 symbols!")
    String username;

    @Size(min = 3, max = 30, message = "Password should be 3 - 30 symbols!")
    String password;
}
