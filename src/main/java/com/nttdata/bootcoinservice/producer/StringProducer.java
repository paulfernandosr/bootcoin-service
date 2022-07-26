package com.nttdata.bootcoinservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcoinservice.dto.TransactionDto;
import com.nttdata.bootcoinservice.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void validateWalletForTransaction(TransactionDto transaction) {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(transaction);
        } catch (JsonProcessingException e) {
            throw new DomainException(HttpStatus.BAD_REQUEST, "Error while serializing transaction");
        }
        kafkaTemplate.send("VALIDATE_WALLET_FOR_TRANSACTION", message);
    }

}
