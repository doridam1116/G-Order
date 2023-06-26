package com.gaubiz.gorder.api.account.service;

import com.gaubiz.gorder.api.account.model.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<?> registerAccount(Account account);

    ResponseEntity<?> loginAccount(Account account);
}
