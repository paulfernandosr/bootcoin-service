package com.nttdata.bootcoinservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@Getter
@RedisHash("BootCoinWall")
@Builder(toBuilder = true)
public class BootCoinWallet implements Serializable {

    @Id
    private String id;
    private String documentType;
    private String documentNumber;

    @Indexed
    private String cellPhoneNumber;

    private String cellPhoneImei;
    private String email;
    private Double balance;

}
