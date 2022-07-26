package com.nttdata.bootcoinservice.controller;

import com.nttdata.bootcoinservice.dto.BootCoinWalletDto;
import com.nttdata.bootcoinservice.service.IBootCoinWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bootcoin-wallets")
public class BootCoinWalletController {

    private final IBootCoinWalletService service;

    @GetMapping("/all")
    public ResponseEntity<List<BootCoinWalletDto>> getAll() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BootCoinWalletDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BootCoinWalletDto> register(@RequestBody BootCoinWalletDto wallet) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.register(wallet));
    }

}
