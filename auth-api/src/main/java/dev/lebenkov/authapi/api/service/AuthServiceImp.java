package dev.lebenkov.authapi.api.service;

import dev.lebenkov.authapi.api.security.JwtUtil;
import dev.lebenkov.authapi.store.dto.AccountDTO;
import dev.lebenkov.authapi.store.dto.AuthDTO;
import dev.lebenkov.authapi.store.enums.Role;
import dev.lebenkov.authapi.store.model.Account;
import dev.lebenkov.authapi.store.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    JwtUtil jwtUtil;
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;
    AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> register(AccountDTO accountDTO) {
        Account account = convertToAccount(accountDTO);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(String.valueOf(Role.ROLE_USER));
        accountRepository.save(account);

        return Map.of("jwt-token", jwtUtil.generateToken(account.getUsername()));
    }

    @Override
    public Map<String, String> login(AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(),
                        authDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        return Map.of("jwt-token", jwtUtil.generateToken(authDTO.getUsername()));
    }

    private Account convertToAccount(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }
}
