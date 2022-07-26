package com.nttdata.bootcoinservice.service.impl;

import com.nttdata.bootcoinservice.dto.TransactionDto;
import com.nttdata.bootcoinservice.dto.BootCoinWalletDto;
import com.nttdata.bootcoinservice.exception.DomainException;
import com.nttdata.bootcoinservice.model.Transaction;
import com.nttdata.bootcoinservice.producer.StringProducer;
import com.nttdata.bootcoinservice.repo.ITransactionRepo;
import com.nttdata.bootcoinservice.service.ITransactionService;
import com.nttdata.bootcoinservice.service.IBootCoinWalletService;
import com.nttdata.bootcoinservice.util.Constants;
import com.nttdata.bootcoinservice.util.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionRepo repo;
    private final IBootCoinWalletService walletService;
    private final StringProducer stringProducer;

    @Override
    public void generate(TransactionDto transaction) {
        BootCoinWalletDto sourceWallet = walletService.getByCellPhoneNumber(transaction.getSourceCellPhoneNumber());
        if (sourceWallet.getBalance() < transaction.getAmount()) {
            throw new DomainException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }
        walletService.getByCellPhoneNumber(transaction.getTargetCellPhoneNumber());
        stringProducer.validateWalletForTransaction(transaction);
    }

    @Override
    public TransactionDto register(TransactionDto transaction) {
        return TransactionMapper.toTransactionDto(
                repo.save(TransactionMapper.toTransaction(transaction.toBuilder()
                        .id(null)
                        .status(Constants.PENDING_STATUS)
                        .build())));
    }

    @Override
    public TransactionDto accept(String transactionId) {
        Transaction transaction = repo.findById(transactionId).orElseThrow(() -> new RuntimeException(Constants.TRANSACTION_NOT_FOUND));
        BootCoinWalletDto sourceWallet = walletService.getByCellPhoneNumber(transaction.getSourceCellPhoneNumber());
        BootCoinWalletDto targetWallet = walletService.getByCellPhoneNumber(transaction.getTargetCellPhoneNumber());
        double bootcoins = transaction.getAmount() / Constants.PURCHASE_PRICE;

        walletService.updateByCellPhoneNumber(sourceWallet.getCellPhoneNumber(),
                sourceWallet.toBuilder().balance(sourceWallet.getBalance() + bootcoins).build());
        walletService.updateByCellPhoneNumber(targetWallet.getCellPhoneNumber(),
                targetWallet.toBuilder().balance(targetWallet.getBalance() - bootcoins).build());

        return TransactionMapper.toTransactionDto(
                repo.save(transaction.toBuilder()
                        .transactionNumber(UUID.randomUUID().toString())
                        .status(Constants.ACCEPTED_STATUS)
                        .build()));
    }

    @Override
    public TransactionDto getByTransactionNumber(String transactionNumber) {
        return repo.findByTransactionNumber(transactionNumber)
                .map(TransactionMapper::toTransactionDto)
                .orElseThrow(() -> new RuntimeException(Constants.TRANSACTION_NOT_FOUND));
    }

    @Override
    public List<TransactionDto> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        repo.findAll().forEach(transactions::add);
        return transactions.stream().map(TransactionMapper::toTransactionDto).collect(Collectors.toList());
    }

    @Override
    public TransactionDto getById(String id) {
        log.info("Retrieving wallet with id {}", id);
        return repo.findById(id)
                .map(TransactionMapper::toTransactionDto)
                .orElseThrow(() -> new RuntimeException(Constants.TRANSACTION_NOT_FOUND));
    }

}
