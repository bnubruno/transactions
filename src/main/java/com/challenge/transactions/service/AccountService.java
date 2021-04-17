package com.challenge.transactions.service;

import java.util.Optional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.challenge.transactions.entity.Account;
import com.challenge.transactions.repository.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {

	private AccountRepository accountRepository;

	public Account save(Account account) {
		return accountRepository.save( account );
	}

	public Optional<Account> findById(Long accountId) {
		return accountRepository.findById( accountId );
	}

}
