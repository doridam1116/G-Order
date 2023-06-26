package com.gaubiz.gorder.security.auth;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public PrincipalDetailService(AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;

    }


    @Override
    public PrincipalDetails loadUserByUsername(String accountSerial) {
        // Repository를 통해 DB에서 userId를 통해 user 값을 가져온다.
        Account account = accountRepository.findAccountBySerial(accountSerial);
        // user 존재 여부 확인
        if(account == null){
            return null;
        }
        return new PrincipalDetails(account);
    }
}
