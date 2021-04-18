package com.challenge.transactions.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.transactions.entity.Account;
import com.challenge.transactions.entity.OperationType;
import com.challenge.transactions.entity.Transaction;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TransactionRespositoryTest {

	@Autowired
	private TransactionRepository repository;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	@Transactional
	public void whenSaveTransactionGenerateEventDate() {
		Account account = createAccount();

		Transaction result = repository.save( Transaction.builder()
				.account( account )
				.operationType( OperationType.A_VISTA )
				.amount( BigDecimal.TEN )
				.build() );

		assertThat( result.getId(), notNullValue() );
		assertThat( result.getEventDate(), notNullValue() );
	}

	private Account createAccount() {
		return accountRepository.save( Account.builder().documentNumber( 12345678L ).build() );
	}
}
