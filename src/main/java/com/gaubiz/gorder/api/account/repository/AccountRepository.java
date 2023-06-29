package com.gaubiz.gorder.api.account.repository;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.model.Sub;

public interface AccountRepository {
    int registerAccount(Account account);

    Account getAccountSerialByTel(Account account);

    Account findAccountBySerial(String accountSerial);

    int addSub(Sub sub);

    Sub selectSubSerial(Sub sub);

    int deleteSubBySerial(String subSerial);

    int updateSubActive(Sub sub);

    int insertAccountBySub(Sub sub);
}
