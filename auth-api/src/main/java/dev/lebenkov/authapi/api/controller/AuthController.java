package dev.lebenkov.authapi.api.controller;

import dev.lebenkov.authapi.api.service.AuthService;
import dev.lebenkov.authapi.api.util.validation.AccountValidator;
import dev.lebenkov.authapi.store.dto.AccountDTO;
import dev.lebenkov.authapi.store.dto.AuthDTO;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/auth")
public class AuthController {

    AccountValidator accountValidator;
    AuthService authService;

    @PostMapping("/register")
    public Map<String, String> performRegistration(
            @RequestBody @Valid AccountDTO accountDTO,
            BindingResult bindingResult) {
        accountValidator.validate(accountDTO, bindingResult);

        if (bindingResult.hasErrors())
            return Map.of("Error", bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .filter(Objects::nonNull)
                    .toList()
                    .toString());


        return authService.register(accountDTO) ;
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(
            @RequestBody @Valid AuthDTO authDTO) {
        return authService.login(authDTO);
    }
}
