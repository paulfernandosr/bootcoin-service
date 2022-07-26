package com.nttdata.bootcoinservice.service;

import com.nttdata.bootcoinservice.dto.TransactionDto;

import java.util.List;

public interface ITransactionService {

    void generate(TransactionDto transaction);

    TransactionDto register(TransactionDto transaction);

    TransactionDto accept(String transactionId);

    TransactionDto getByTransactionNumber(String transactionNumber);

    List<TransactionDto> getAll();

    TransactionDto getById(String id);

}
