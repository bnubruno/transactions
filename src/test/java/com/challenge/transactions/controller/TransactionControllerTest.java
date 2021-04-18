package com.challenge.transactions.controller;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.transactions.TransactionsApplication;
import com.challenge.transactions.dto.request.TransactionRequestDTO;
import com.challenge.transactions.entity.Account;
import com.challenge.transactions.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionsApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TransactionControllerTest {

	public static final long DOCUMENT_NUMBER = 12345678900L;
	private static final String URL = "/transactions";
	@Autowired
	private MockMvc mvc;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Transactional
	public void whenCreateTransaction() throws Exception {
		Account account = accountRepository.save( buildAccount() );
		TransactionRequestDTO transactionDTO = buildTransactionRequestDTO( account.getId() );

		mvc.perform( post( URL )
				.contentType( MediaType.APPLICATION_JSON )
				.content( objectMapper.writeValueAsString( transactionDTO ) ) )
				.andExpect( status().isCreated() );
	}

	@Test
	@Transactional
	public void whenCreateTransactionWithNullableAccountId() throws Exception {
		TransactionRequestDTO transactionDTO = buildTransactionRequestDTO( null );

		mvc.perform( post( URL )
				.contentType( MediaType.APPLICATION_JSON )
				.content( objectMapper.writeValueAsString( transactionDTO ) ) )
				.andDo( print() )
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath( "$.messages[0]", equalTo( "Account Id must be not null" ) ) );
	}

	private Account buildAccount() {
		return Account.builder().documentNumber( DOCUMENT_NUMBER ).build();
	}

	private TransactionRequestDTO buildTransactionRequestDTO(Long accountId) {
		return TransactionRequestDTO.builder()
				.accountId( accountId )
				.operationTypeId( 1 )
				.amount( valueOf( 50 ) )
				.build();
	}

}
