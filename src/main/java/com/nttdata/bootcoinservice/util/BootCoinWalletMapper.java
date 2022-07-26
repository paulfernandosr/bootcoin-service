package com.nttdata.bootcoinservice.util;

import com.nttdata.bootcoinservice.dto.BootCoinWalletDto;
import com.nttdata.bootcoinservice.model.BootCoinWallet;

public class BootCoinWalletMapper {

    private BootCoinWalletMapper() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static BootCoinWallet toWallet(BootCoinWalletDto bootcoinWalletDto) {
        return BootCoinWallet.builder()
                .id(bootcoinWalletDto.getId())
                .documentType(bootcoinWalletDto.getDocumentType())
                .documentNumber(bootcoinWalletDto.getDocumentNumber())
                .cellPhoneNumber(bootcoinWalletDto.getCellPhoneNumber())
                .cellPhoneImei(bootcoinWalletDto.getCellPhoneImei())
                .email(bootcoinWalletDto.getEmail())
                .balance(bootcoinWalletDto.getBalance())
                .build();
    }

    public static BootCoinWalletDto toWalletDto(BootCoinWallet bootcoinWallet) {
        return BootCoinWalletDto.builder()
                .id(bootcoinWallet.getId())
                .documentType(bootcoinWallet.getDocumentType())
                .documentNumber(bootcoinWallet.getDocumentNumber())
                .cellPhoneNumber(bootcoinWallet.getCellPhoneNumber())
                .cellPhoneImei(bootcoinWallet.getCellPhoneImei())
                .email(bootcoinWallet.getEmail())
                .balance(bootcoinWallet.getBalance())
                .build();
    }

}
