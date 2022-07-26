package com.nttdata.bootcoinservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionDto {

    private String id;
    private String transactionNumber;
    private Double amount;
    private String paymentMethod;
    private String sourceCellPhoneNumber;
    private String targetCellPhoneNumber;
    private String status;

}
