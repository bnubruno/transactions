package com.challenge.transactions.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import com.challenge.transactions.dto.request.AccountRequestDTO;
import com.challenge.transactions.dto.response.AccountResponseDTO;
import com.challenge.transactions.entity.Account;

public class AccountMapperTest {

	private static final Long DOCUMENT_NUMBER = 12345678L;
	private static final Long ACCOUNT_ID = 1L;

	private AccountMapper mapper = new AccountMapperImpl();

	@Test
	public void whenMapToEntity() {
		AccountRequestDTO dto = buildAccountRequestDTO();

		Account result = mapper.toEntity( dto );

		assertThat( result.getId(), Matchers.nullValue() );
		assertThat( result.getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );
	}

	@Test
	public void whenMapToDTO() {
		Account account = buildAccount();

		AccountResponseDTO result = mapper.toDTO( account );

		assertThat( result.getAccountId(), equalTo( ACCOUNT_ID ) );
		assertThat( result.getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );

	}

	private Account buildAccount() {
		return Account.builder()
				.id( ACCOUNT_ID )
				.documentNumber( DOCUMENT_NUMBER )
				.build();
	}

	private AccountRequestDTO buildAccountRequestDTO() {
		return AccountRequestDTO.builder()
				.documentNumber( DOCUMENT_NUMBER.toString() )
				.build();
	}

}
