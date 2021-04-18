package com.challenge.transactions.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.challenge.transactions.entity.Transaction;
import com.challenge.transactions.repository.TransactionRepository;

@Service
@AllArgsConstructor
public class TransactionService {

	private TransactionRepository transactionRepository;

	public Transaction save(Transaction transaction) {
		transaction.setAmount( transaction.getOperationType().getAmountMapper().map( transaction.getAmount() ) );
		return transactionRepository.save( transaction );
	}
}
