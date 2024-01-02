package dev.lebenkov.authapi.api.service;

import dev.lebenkov.authapi.store.dto.AccountDTO;
import dev.lebenkov.authapi.store.dto.AuthDTO;

import java.util.Map;

public interface AuthService {
    Map<String, String> register(AccountDTO accountDTO);
    Map<String, String> login(AuthDTO authDTO);
}
