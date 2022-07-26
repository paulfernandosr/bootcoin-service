package com.nttdata.bootcoinservice.controller;

import com.nttdata.bootcoinservice.dto.TransactionDto;
import com.nttdata.bootcoinservice.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final ITransactionService service;

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAll() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> generate(@RequestBody TransactionDto transaction) {
        service.generate(transaction);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<TransactionDto> accept(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.accept(id));
    }

}
