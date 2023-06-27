package com.gaubiz.gorder.api.account.service.logic;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.model.Sub;
import com.gaubiz.gorder.api.account.repository.AccountRepository;
import com.gaubiz.gorder.api.account.service.AccountService;
import com.gaubiz.gorder.msg.HttpStatusMsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceLogic implements AccountService {

    @Value("${message.005}")
    private String error005;

    private final AccountRepository accountRepository;



    public AccountServiceLogic(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public ResponseEntity<?> registerAccount(Account account) {
        account.setAccountSerial("G" + account.getAccountTel());
        int result = accountRepository.registerAccount(account);
        if (result > 0) {
            Account accountSerial =  accountRepository.getAccountSerialByTel(account);
            return ResponseEntity.ok().body(accountSerial.getAccountSerial());
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error005);
        }
    }

    @Override
    public ResponseEntity<?> loginAccount(Account account) {
//        PrincipalDetails principalDetails = principalDetailService.loadUserByUsername(user.getUserId());
        return null;
    }

    @Override
    public ResponseEntity<?> addSub(Sub sub) {
        int result = accountRepository.addSub(sub);
        if(result > 0){
            Sub subSerial = accountRepository.selectSubSerial(sub);
            return ResponseEntity.ok().body(subSerial.getSubSerial());
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(HttpStatusMsg.HTTP_BAD_REQUEST);
        }
    }
}
