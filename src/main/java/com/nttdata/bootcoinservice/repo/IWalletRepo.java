package com.nttdata.bootcoinservice.repo;

import com.nttdata.bootcoinservice.model.BootCoinWallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWalletRepo extends CrudRepository<BootCoinWallet, String> {

    List<BootCoinWallet> findByCellPhoneNumber(String cellPhoneNumber);

}
