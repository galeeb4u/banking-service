package com.greenfin.fundtransfer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.greenfin.fundtransfer.model.TransactionStatus;
import com.greenfin.fundtransfer.model.TransferType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundTransferDto {

    private String transactionReference;

    private String fromAccount;

    private String toAccount;

    private BigDecimal amount;

    private TransactionStatus status;

    private TransferType transferType;

    private LocalDateTime transferredOn;
}
