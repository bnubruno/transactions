package com.challenge.transactions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactions.dto.response.TransactionRequestDTO;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody TransactionRequestDTO dto) {
		System.out.println( dto );
		return;
	}

}
