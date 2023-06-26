package com.gaubiz.gorder.api.account.repository;

import com.gaubiz.gorder.api.account.model.Account;

public interface AccountRepository {
    int registerAccount(Account account);

    Account getAccountSerialByTel(Account account);

    Account findAccountBySerial(String accountSerial);
}
