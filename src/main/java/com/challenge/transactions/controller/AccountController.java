package com.challenge.transactions.controller;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactions.dto.request.AccountRequestDTO;
import com.challenge.transactions.dto.response.AccountResponseDTO;
import com.challenge.transactions.exception.NotFoundException;
import com.challenge.transactions.mapper.AccountMapper;
import com.challenge.transactions.service.AccountService;

@RestController
@AllArgsConstructor
public class AccountController {

	private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found";

	private AccountMapper accountMapper;
	private AccountService accountService;

	@PostMapping("/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountResponseDTO save(@Valid @RequestBody AccountRequestDTO request) {
		return accountMapper.toDTO( accountService.save( accountMapper.toEntity( request ) ) );
	}

	@GetMapping("/accounts/{id}")
	public AccountResponseDTO findById(@PathVariable("id") Long id) {
		return accountService.findById( id )
				.map( accountMapper::toDTO )
				.orElseThrow( () -> new NotFoundException( ACCOUNT_NOT_FOUND_MESSAGE ) );
	}

}
