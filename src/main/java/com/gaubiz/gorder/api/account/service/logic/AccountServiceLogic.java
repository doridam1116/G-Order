package com.gaubiz.gorder.api.account.service.logic;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.model.Sub;
import com.gaubiz.gorder.api.account.repository.AccountRepository;
import com.gaubiz.gorder.api.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.gaubiz.gorder.config.PropertyConfig.getMessageSource;

@Service
public class AccountServiceLogic implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceLogic(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public ResponseEntity<?> registerAccount(Account account) {
        account.setAccountSerial("G" + account.getAccountTel());
        int result = accountRepository.registerAccount(account);
        if (result > 0) {
            Account accountSerial = accountRepository.getAccountSerialByTel(account);
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()) + accountSerial.getAccountSerial());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> addSub(Sub sub) {
        int result = accountRepository.addSub(sub);
        if (result > 0) {
            Sub subSerial = accountRepository.selectSubSerial(sub);
            result = accountRepository.insertAccountBySub(subSerial);
            if (result > 0) {
                return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()) + subSerial.getSubSerial());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> deleteSubBySerial(String subSerial) {
        int result = accountRepository.deleteSubBySerial(subSerial);
        if (result > 0) {
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }

    @Override
    public ResponseEntity<?> updateSubActive(Sub sub) {
        int result = accountRepository.updateSubActive(sub);
        if (result > 0) {
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
        }
    }
}
