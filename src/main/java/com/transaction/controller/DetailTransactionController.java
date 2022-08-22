package com.transaction.controller;

import com.transaction.dto.DetailTransactionDto;
import com.transaction.dto.TransactionDto;
import com.transaction.service.DetailTransactionService;
import com.transaction.service.TransactionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class DetailTransactionController {

    @Autowired
    private DetailTransactionService detailTransactionService;

    @SneakyThrows(Exception.class)
    @PostMapping(path = "/detailtransactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTransaction(@RequestBody DetailTransactionDto.CreateDetailTransactionDto dto){
        log.info("POST /api/v1/detailtransactions is called");
        return detailTransactionService.create(dto);
    }

    @SneakyThrows
    @GetMapping(path = "/detailtransactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailTransaction() {

        log.info("GET /api/v1/transactions is called");
        return detailTransactionService.get();
    }
//
//    @SneakyThrows
//    @PutMapping(path = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> updateTransaction(@RequestBody TransactionDto.UpdateTransactionDto dto) {
//
//        log.info("PUT /api/v1/transactions is called...");
//        return transactionService.update(dto);
//    }
//
//    @SneakyThrows
//    @DeleteMapping(path = "/transactions")
//    public ResponseEntity<Object> deleteTransaction(@RequestBody TransactionDto.DeleteTransactionDto dto) {
//
//        log.info("DELETE /api/v1/transaction/{} is called", dto);
//
//        return transactionService.delete(dto);
//    }
}
