package com.transaction.service;

import com.transaction.dto.TransactionDto;
import com.transaction.errorresponse.ErrorResponse;
import com.transaction.model.Transaction;
import com.transaction.repository.TransactionRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @SneakyThrows
    public ResponseEntity<Object> create(TransactionDto.CreateTransactionDto dto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Transaction transaction = new Transaction();
        transaction.setCreatedBy(dto.getCreatedBy());
        transaction.setReaderId(dto.getReaderId());
        transaction.setTransaction_date(dto.getTransaction_date());
        transaction.setBorrow_start(dto.getBorrow_start());
        transaction.setBorrow_end(dto.getBorrow_end());
        transaction.setReturned_date(dto.getReturned_date());
        transaction.setStatus(dto.getStatus());
        transaction.setColor(dto.getColor());
        transactionRepository.save(transaction);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 201);
        res.put("message", "success");
        res.put("data", transaction);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
    }

    @SneakyThrows
    public ResponseEntity<Object> get(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>(transactionRepository.findAll());

        if (transactions == null){
            ErrorResponse errorResponse = new ErrorResponse("9999", "data not found");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(errorResponse);
        }else {
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("code", 200);
            res.put("message", "success");
            res.put("data", transactions);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
        }
    }

    @SneakyThrows
    public ResponseEntity<Object> update(TransactionDto.UpdateTransactionDto dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Transaction transaction = transactionRepository.findById(dto.getId()).orElse(null);
        if (Optional.ofNullable(transaction).isPresent()){
            transaction.setCreatedBy(dto.getCreatedBy());
            transaction.setReaderId(dto.getReaderId());
            transaction.setBorrow_start(dto.getBorrow_start());
            transaction.setBorrow_end(dto.getBorrow_end());
            transaction.setReturned_date(dto.getReturned_date());
            transaction.setStatus(dto.getStatus());
            transaction.setColor(dto.getColor());
            transactionRepository.save(transaction);
        }else {
            ErrorResponse errorResponse = new ErrorResponse("9999", "data not found");
        }

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("code", 200);
        res.put("message", "success");
        res.put("data", transaction);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(res);
    }

    @SneakyThrows(Exception.class)
    public ResponseEntity<Object> delete(TransactionDto.DeleteTransactionDto dto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> res = new HashMap<String, Object>();

        Transaction transaction = transactionRepository.findById(dto.getId()).orElse(null);

        if(Optional.ofNullable(transaction).isPresent()){
            transaction.setIsDeleted(1);
            transaction.setDeletedAt(new Date());
            transactionRepository.save(transaction);
        }else{
            ErrorResponse errorResponse = new ErrorResponse("9999", "data not found");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(errorResponse);
        }

        res.put("code", 200);
        res.put("message", "success deleted transaction");

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
    }

}
