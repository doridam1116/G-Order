package com.gaubiz.gorder.api.account.service;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.model.Sub;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<?> registerAccount(Account account);


    ResponseEntity<?> addSub(Sub sub);

    ResponseEntity<?> deleteSubBySerial(String subSerial);

    ResponseEntity<?> updateSubActive(Sub sub);

    ResponseEntity<?> readSub(String accountSerial);

    ResponseEntity<?> updateSub(Sub sub);
}
