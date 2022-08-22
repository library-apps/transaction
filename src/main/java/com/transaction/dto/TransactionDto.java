package com.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class TransactionDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateTransactionDto {

        private Integer createdBy;
        private Integer readerId;
        private Date transaction_date;
        private Date borrow_start;
        private Date borrow_end;
        private Date returned_date;
        private String status;
        private String color;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateTransactionDto {
        private Long id;
        private Integer createdBy;
        private Integer readerId;
        private Date transaction_date;
        private Date borrow_start;
        private Date borrow_end;
        private Date returned_date;
        private String status;
        private String color;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteTransactionDto {
        private Long id;
    }

}
