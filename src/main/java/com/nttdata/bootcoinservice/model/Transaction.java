package com.nttdata.bootcoinservice.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("Transaction")
@Builder(toBuilder = true)
public class Transaction {

    @Id
    private String id;
    private String transactionNumber;
    private Double amount;
    private String paymentMethod;
    private String sourceCellPhoneNumber;
    private String targetCellPhoneNumber;
    private String status;

}
