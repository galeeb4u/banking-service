package com.greenfin.account.service.model.mapper;

import org.springframework.beans.BeanUtils;
import com.greenfin.account.service.model.dto.AccountDto;
import com.greenfin.account.service.model.entity.Account;

import java.util.Objects;

public class AccountMapper extends BaseMapper<Account, AccountDto> {


    @Override
    public Account convertToEntity(AccountDto dto, Object... args) {
        Account account = new Account();
        if(!Objects.isNull(dto)){
            BeanUtils.copyProperties(dto, account);
        }
        return account;
    }

    @Override
    public AccountDto convertToDto(Account entity, Object... args) {

        AccountDto accountDto = new AccountDto();
        if(!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, accountDto);
        }
        return accountDto;
    }
}
