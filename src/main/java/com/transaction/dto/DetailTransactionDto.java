package com.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DetailTransactionDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateDetailTransactionDto {
        private Long transaction_id;
        private Integer book_id;
    }
}
