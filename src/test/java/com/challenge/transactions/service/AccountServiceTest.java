package com.challenge.transactions.service;

import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.transactions.entity.Account;
import com.challenge.transactions.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

	private static final long DOCUMENT_NUMBER = 12345678900L;

	@InjectMocks
	private AccountService service;

	@Mock
	private AccountRepository repository;

	@Test
	public void whenCreate() {
		Account account = buildAccount( null );
		when( repository.save( account ) ).thenReturn( buildAccount( 100L ) );

		Account save = service.save( account );

		assertThat( save.getId(), equalTo( 100L ) );
		assertThat( save.getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );
	}

	@Test
	public void whenFindById() {
		when( repository.findById( 100L ) ).thenReturn( of( buildAccount( 100L ) ) );

		Optional<Account> account = service.findById( 100L );

		assertThat( account.isPresent(), is( true ) );
		assertThat( account.get().getId(), equalTo( 100L ) );
		assertThat( account.get().getDocumentNumber(), equalTo( DOCUMENT_NUMBER ) );
	}

	private Account buildAccount(Long accountId) {
		return Account.builder().id( accountId ).documentNumber( DOCUMENT_NUMBER ).build();
	}

}
