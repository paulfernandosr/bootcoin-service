package com.nttdata.bootcoinservice.repo;

import com.nttdata.bootcoinservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITransactionRepo extends CrudRepository<Transaction, String> {

    Optional<Transaction> findByTransactionNumber(String transactionNumber);

}
