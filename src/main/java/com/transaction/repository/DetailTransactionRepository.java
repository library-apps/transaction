package com.transaction.repository;

import com.transaction.model.DetailTransaction;
import com.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailTransactionRepository extends JpaRepository<DetailTransaction, Long> {
}
