package com.challenge.transactions.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.challenge.transactions.dto.request.TransactionRequestDTO;
import com.challenge.transactions.dto.response.TransactionResponseDTO;
import com.challenge.transactions.entity.Account;
import com.challenge.transactions.entity.OperationType;
import com.challenge.transactions.entity.Transaction;

public class TransactionMapperTest {

	private static final Long DOCUMENT_NUMBER = 12345678L;
	private static final Long ACCOUNT_ID = 1L;
	private static final Long OPERATION_TYPE_ID = 1L;
	private static final Long TRANSACTION_ID = 600L;

	private TransactionMapper mapper = new TransactionMapperImpl();

	@Test
	public void whenMapToEntity() {
		TransactionRequestDTO dto = buildTransactionRequestDTO();
		Account account = buildAccount();
		OperationType operationType = OperationType.A_VISTA;

		Transaction result = mapper.toEntity( dto, account, operationType );

		assertThat( result.getAmount(), comparesEqualTo( BigDecimal.TEN ) );
		assertThat( result.getOperationType(), equalTo( OperationType.A_VISTA ) );
		assertThat( result.getEventDate(), nullValue() );
		assertThat( result.getId(), nullValue() );
	}

	@Test
	public void whenMapToDTO() {
		Transaction transaction = buildTransaction();

		TransactionResponseDTO result = mapper.toDTO( transaction );

		assertThat( result.getAmount(), comparesEqualTo( BigDecimal.TEN ) );
		assertThat( result.getOperationTypeId(), equalTo( OPERATION_TYPE_ID ) );
		assertThat( result.getEventDate(), equalTo( getEventDate() ) );
	}

	private Transaction buildTransaction() {
		return Transaction.builder()
				.id( TRANSACTION_ID )
				.operationType( OperationType.A_VISTA )
				.amount( BigDecimal.TEN )
				.account( buildAccount() )
				.eventDate( getEventDate() )
				.build();
	}

	private LocalDateTime getEventDate() {
		return LocalDateTime.of( 2020, 1, 1, 10, 20 );
	}

	private TransactionRequestDTO buildTransactionRequestDTO() {
		return TransactionRequestDTO.builder()
				.amount( BigDecimal.TEN )
				.operationTypeId( 1 )
				.accountId( 1L )
				.build();
	}

	private Account buildAccount() {
		return Account.builder()
				.id( ACCOUNT_ID )
				.documentNumber( DOCUMENT_NUMBER )
				.build();
	}

}
