package com.challenge.transactions.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.transactions.TransactionsApplication;
import com.challenge.transactions.dto.request.AccountRequestDTO;
import com.challenge.transactions.entity.Account;
import com.challenge.transactions.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionsApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class AccountControllerTest {

	private static final String URL = "/accounts";
	private static final String DOCUMENT_NUMBER_STRING = "12345678900";
	private static final Long DOCUMENT_NUMBER = 12345678900L;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	@Transactional
	public void whenCreateAccount() throws Exception {
		AccountRequestDTO accountDTO = AccountRequestDTO.builder().documentNumber( DOCUMENT_NUMBER_STRING ).build();

		mvc.perform( post( URL )
				.contentType( MediaType.APPLICATION_JSON )
				.content( objectMapper.writeValueAsString( accountDTO ) ) )
				.andExpect( status().isCreated() )
				.andExpect( jsonPath( "$.document_number", equalTo( DOCUMENT_NUMBER ) ) );
	}

	@Test
	@Transactional
	public void whenCreateAccountWithInvalidDocumentNumberThenReturnBadRequest() throws Exception {
		AccountRequestDTO accountDTO = AccountRequestDTO.builder().documentNumber( null ).build();

		mvc.perform( post( URL )
				.contentType( MediaType.APPLICATION_JSON )
				.content( objectMapper.writeValueAsString( accountDTO ) ) )
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath( "$.messages[0]", equalTo( "Document number must be not null" ) ) );
	}

	@Test
	@Transactional
	public void whenFindAccountAndReturnNotFound() throws Exception {
		mvc.perform( get( "/accounts/1" )
				.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( MockMvcResultMatchers.status().isNotFound() )
				.andExpect( jsonPath( "$.messages[0]", equalTo( "Account not found" ) ) );
	}

	@Test
	@Transactional
	public void whenFindAccountAndReturnOK() throws Exception {
		Account account = accountRepository.save( Account.builder().documentNumber( DOCUMENT_NUMBER ).build() );

		mvc.perform( MockMvcRequestBuilders
				.get( "/accounts/{id}", account.getId() )
				.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath( "$.document_number" ).value( DOCUMENT_NUMBER ) );
	}

}
