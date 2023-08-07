package com.gaubiz.gorder.api.account.repository;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.model.Sub;

import java.util.List;

public interface AccountRepository {
    int registerAccount(Account account);

    Account getAccountSerialByTel(Account account);

    Account findAccountBySerial(Account account);

    int addSub(Sub sub);

    Sub selectSubSerial(Sub sub);

    int deleteSubBySerial(String subSerial);

    int updateSubActive(Sub sub);

    int insertAccountBySub(Sub sub);

    List<Sub> readSub(String accountSerial);

    int updateSub(Sub sub);
}
