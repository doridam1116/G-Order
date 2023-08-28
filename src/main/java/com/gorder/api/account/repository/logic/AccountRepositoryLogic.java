package com.gorder.api.account.repository.logic;

import com.gorder.api.account.model.Account;
import com.gorder.api.account.model.Sub;
import com.gorder.api.account.repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Account findAccountBySerial(Account account) {
        return session.selectOne("AccountMapper.findAccountBySerial",account);
    }

    @Override
    public int addSub(Sub sub) {
        return session.insert("AccountMapper.insertSub",sub);
    }

    @Override
    public Sub selectSubSerial(Sub sub) {
        return session.selectOne("AccountMapper.selectSubSerial",sub);
    }

    @Override
    public int deleteSubBySerial(String subSerial) {
        return session.delete("AccountMapper.deleteSubBySerial", subSerial);
    }

    @Override
    public int updateSubActive(Sub sub) {
        return session.update("AccountMapper.updateSubActive",sub);
    }

    @Override
    public int insertAccountBySub(Sub sub) {
        return session.insert("AccountMapper.insertAccountBySub",sub);
    }

    @Override
    public List<Sub> readSub(String accountSerial) {
        return session.selectList("AccountMapper.selectSubByAccountSerial",accountSerial);
    }

    @Override
    public int updateSub(Sub sub) {
        return session.update("AccountMapper.updateSub",sub);
    }
}
