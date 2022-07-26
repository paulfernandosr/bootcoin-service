package com.nttdata.bootcoinservice.service;

import com.nttdata.bootcoinservice.dto.BootCoinWalletDto;

import java.util.List;

public interface IBootCoinWalletService {

    List<BootCoinWalletDto> getAll();

    BootCoinWalletDto getById(String id);

    BootCoinWalletDto getByCellPhoneNumber(String cellPhoneNumber);

    BootCoinWalletDto updateByCellPhoneNumber(String cellPhoneNumber, BootCoinWalletDto wallet);

    BootCoinWalletDto register(BootCoinWalletDto wallet);

}
