package com.challenge.transactions.entity;

import static java.util.Arrays.stream;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.challenge.transactions.mapper.AmountMapper;
import com.challenge.transactions.mapper.NegativeAmountMapper;
import com.challenge.transactions.mapper.PositiveAmountMapper;

@Getter
@AllArgsConstructor
public enum OperationType {

	A_VISTA( 1, "COMPRA A VISTA", new NegativeAmountMapper() ),
	PARCELADA( 2, "COMPRA PARCELADA", new NegativeAmountMapper() ),
	SAQUE( 3, "SAQUE", new NegativeAmountMapper() ),
	PAGAMENTO( 4, "PAGAMENTO", new PositiveAmountMapper() );

	private Integer id;
	private String description;
	private AmountMapper amountMapper;

	public static Optional<OperationType> getById(Integer id) {
		return stream( values() )
				.filter( value -> value.getId().equals( id ) )
				.findFirst();

	}

}
