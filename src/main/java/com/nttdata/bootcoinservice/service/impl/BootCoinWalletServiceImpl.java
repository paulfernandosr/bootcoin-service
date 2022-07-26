package com.nttdata.bootcoinservice.service.impl;

import com.nttdata.bootcoinservice.dto.BootCoinWalletDto;
import com.nttdata.bootcoinservice.model.BootCoinWallet;
import com.nttdata.bootcoinservice.repo.IWalletRepo;
import com.nttdata.bootcoinservice.service.IBootCoinWalletService;
import com.nttdata.bootcoinservice.util.BootCoinWalletMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BootCoinWalletServiceImpl implements IBootCoinWalletService {

    private final IWalletRepo repo;

    @Override
    public List<BootCoinWalletDto> getAll() {
        List<BootCoinWallet> bootCoinWallets = new ArrayList<>();
        repo.findAll().forEach(bootCoinWallets::add);
        return bootCoinWallets.stream().map(BootCoinWalletMapper::toWalletDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable("walletCache")
    public BootCoinWalletDto getById(String id) {
        log.info("Retrieving wallet with id {}", id);
        return repo.findById(id)
                .map(BootCoinWalletMapper::toWalletDto)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public BootCoinWalletDto getByCellPhoneNumber(String cellPhoneNumber) {
        return repo.findByCellPhoneNumber(cellPhoneNumber).stream().findFirst()
                .map(BootCoinWalletMapper::toWalletDto)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public BootCoinWalletDto updateByCellPhoneNumber(String cellPhoneNumber, BootCoinWalletDto wallet) {
        BootCoinWalletDto modifiedWallet = getByCellPhoneNumber(cellPhoneNumber).toBuilder()
                .documentType(wallet.getDocumentType())
                .documentNumber(wallet.getDocumentNumber())
                .cellPhoneNumber(wallet.getCellPhoneNumber())
                .cellPhoneImei(wallet.getCellPhoneImei())
                .email(wallet.getEmail())
                .balance(wallet.getBalance())
                .build();
        return BootCoinWalletMapper.toWalletDto(repo.save(BootCoinWalletMapper.toWallet(modifiedWallet)));
    }

    @Override
    public BootCoinWalletDto register(BootCoinWalletDto wallet) {
        return BootCoinWalletMapper.toWalletDto(repo.save(BootCoinWalletMapper.toWallet(wallet)));
    }

}
