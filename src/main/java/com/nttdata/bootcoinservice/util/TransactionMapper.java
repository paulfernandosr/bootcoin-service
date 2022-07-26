package com.nttdata.bootcoinservice.util;

import com.nttdata.bootcoinservice.dto.TransactionDto;
import com.nttdata.bootcoinservice.model.Transaction;

public class TransactionMapper {

    private TransactionMapper() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static Transaction toTransaction(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.getId())
                .transactionNumber(transactionDto.getTransactionNumber())
                .amount(transactionDto.getAmount())
                .paymentMethod(transactionDto.getPaymentMethod())
                .sourceCellPhoneNumber(transactionDto.getSourceCellPhoneNumber())
                .targetCellPhoneNumber(transactionDto.getTargetCellPhoneNumber())
                .status(transactionDto.getStatus())
                .build();
    }

    public static TransactionDto toTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .transactionNumber(transaction.getTransactionNumber())
                .amount(transaction.getAmount())
                .paymentMethod(transaction.getPaymentMethod())
                .sourceCellPhoneNumber(transaction.getSourceCellPhoneNumber())
                .targetCellPhoneNumber(transaction.getTargetCellPhoneNumber())
                .status(transaction.getStatus())
                .build();
    }

}
