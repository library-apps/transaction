package com.transaction.service;

import com.transaction.dto.DetailTransactionDto;
import com.transaction.dto.TransactionDto;
import com.transaction.errorresponse.ErrorResponse;
import com.transaction.model.DetailTransaction;
import com.transaction.model.Transaction;
import com.transaction.repository.DetailTransactionRepository;
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
public class DetailTransactionService {

    @Autowired
    private DetailTransactionRepository detailTransactionRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @SneakyThrows
    public ResponseEntity<Object> create(DetailTransactionDto.CreateDetailTransactionDto dto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Transaction transaction = transactionRepository.findById(dto.getTransaction_id()).orElse(null);

        DetailTransaction detailTransaction = new DetailTransaction();
        detailTransaction.setTransaction_id(transaction.getId());
        detailTransaction.setBook_id(dto.getBook_id());
        detailTransactionRepository.save(detailTransaction);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 201);
        res.put("message", "success");
        res.put("data", detailTransaction);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
    }

    @SneakyThrows
    public ResponseEntity<Object> get(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ArrayList<DetailTransaction> detailTransactions = new ArrayList<DetailTransaction>(detailTransactionRepository.findAll());

        if (detailTransactions == null){
            ErrorResponse errorResponse = new ErrorResponse("9999", "data not found");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(errorResponse);
        }else {
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("code", 200);
            res.put("message", "success");
            res.put("data", detailTransactions);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
        }
    }

}
