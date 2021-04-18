package com.challenge.transactions.controller;

import static java.util.Optional.ofNullable;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactions.dto.request.TransactionRequestDTO;
import com.challenge.transactions.dto.response.TransactionResponseDTO;
import com.challenge.transactions.entity.Account;
import com.challenge.transactions.entity.OperationType;
import com.challenge.transactions.entity.Transaction;
import com.challenge.transactions.exception.BadRequestException;
import com.challenge.transactions.mapper.TransactionMapper;
import com.challenge.transactions.service.AccountService;
import com.challenge.transactions.service.TransactionService;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

	private AccountService accountService;
	private TransactionMapper transactionMapper;
	private TransactionService transactionService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionResponseDTO save(@RequestBody TransactionRequestDTO dto) {
		Account account = findAccount( dto );
		OperationType operationType = findOperationType( dto );
		Transaction entity = transactionService.save( transactionMapper.toEntity( dto, account, operationType ) );
		return transactionMapper.toDTO(entity);
	}

	private OperationType findOperationType(TransactionRequestDTO dto) {
		return ofNullable( dto.getOperationTypeId() )
				.flatMap( OperationType::getById )
				.orElseThrow( () -> new BadRequestException( "Operation type not found" ) );
	}

	private Account findAccount(TransactionRequestDTO dto) {
		return ofNullable( dto.getAccountId() )
				.flatMap( accountService::findById )
				.orElseThrow( () -> new BadRequestException( "Account not found" ) );
	}

}
