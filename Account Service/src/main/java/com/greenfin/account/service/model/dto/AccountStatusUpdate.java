package com.greenfin.account.service.model.dto;

import lombok.Data;
import com.greenfin.account.service.model.AccountStatus;

@Data
public class AccountStatusUpdate {
    AccountStatus accountStatus;
}
