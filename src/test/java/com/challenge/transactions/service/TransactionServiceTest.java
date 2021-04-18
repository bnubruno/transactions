package com.challenge.transactions.service;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.transactions.entity.Account;
import com.challenge.transactions.entity.OperationType;
import com.challenge.transactions.entity.Transaction;
import com.challenge.transactions.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	private static final long DOCUMENT_NUMBER = 12345678900L;

	@InjectMocks
	private TransactionService service;

	@Mock
	private TransactionRepository repository;

	@Captor
	private ArgumentCaptor<Transaction> transactionCaptor;

	@Test
	public void whenSaveWithOperationTypeAvistaAmountMustBe50Negative() {
		assertAmountByOperationType( OperationType.A_VISTA, -50 );
	}

	@Test
	public void whenSaveWithOperationTypePagamentoAmountMustBe50Positive() {
		assertAmountByOperationType( OperationType.PAGAMENTO, 50 );
	}

	@Test
	public void whenSaveWithOperationTypeSaqueAmountMustBe50Negative() {
		assertAmountByOperationType( OperationType.SAQUE, -50 );
	}

	@Test
	public void whenSaveWithOperationTypeParceladaAmountMustBe50Negative() {
		assertAmountByOperationType( OperationType.PARCELADA, -50 );
	}

	private void assertAmountByOperationType(OperationType saque, int i) {
		Account account = buildAccount( DOCUMENT_NUMBER );
		Transaction transactionToSave = buildTransaction( account, saque );
		Mockito.when( repository.save( transactionCaptor.capture() ) ).thenReturn( transactionToSave );

		service.save( transactionToSave );

		Transaction transactionCaptured = transactionCaptor.getValue();
		assertThat( transactionCaptured.getAmount(), Matchers.comparesEqualTo( valueOf( i ) ) );
	}

	private Account buildAccount(long documentNumber) {
		return Account.builder()
				.documentNumber( documentNumber )
				.build();
	}

	private Transaction buildTransaction(Account account, OperationType operationType) {
		return Transaction.builder()
				.account( account )
				.operationType( operationType )
				.amount( valueOf( 50 ) )
				.build();
	}

}
