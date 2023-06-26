package com.gaubiz.gorder.api.account.repository.logic;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryLogic implements AccountRepository {
    private final SqlSession session;

    public AccountRepositoryLogic(SqlSession session) {
        this.session = session;
    }

    @Override
    public int registerAccount(Account account) {
        return session.insert("AccountMapper.insertAccount",account);
    }

    @Override
    public Account getAccountSerialByTel(Account account) {
        return session.selectOne("AccountMapper.getAccountSerialByTel",account);
    }

    @Override
    public Account findAccountBySerial(String accountSerial) {
        return session.selectOne("AccountMapper.findAccountBySerial",accountSerial);
    }
}
