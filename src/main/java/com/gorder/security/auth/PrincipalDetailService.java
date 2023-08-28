package com.gorder.security.auth;

import com.gorder.api.account.model.Account;
import com.gorder.api.account.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public PrincipalDetailService(AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;

    }



    public PrincipalDetails loadUserByUsername(Account account) {
        // Repository를 통해 DB에서 userId를 통해 user 값을 가져온다.
        Account accountResult = accountRepository.findAccountBySerial(account);
        // user 존재 여부 확인
        if(accountResult == null){
            return null;
        }
        return new PrincipalDetails(accountResult);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
