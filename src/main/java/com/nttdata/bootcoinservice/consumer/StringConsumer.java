package com.nttdata.bootcoinservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcoinservice.dto.TransactionDto;
import com.nttdata.bootcoinservice.exception.DomainException;
import com.nttdata.bootcoinservice.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringConsumer {

    private final ITransactionService transactionService;

    @KafkaListener(topics = "VALIDATED_WALLET_FOR_TRANSACTION", groupId = "consumer")
    public void validatedWalletForTransaction(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        TransactionDto transaction;
        try {
            transaction = objectMapper.readValue(message, TransactionDto.class);
        } catch (JsonProcessingException e) {
            throw new DomainException(HttpStatus.BAD_REQUEST, "Error while deserializing transaction");
        }
        transactionService.register(transaction);
    }

}
