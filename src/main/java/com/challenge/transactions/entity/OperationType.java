package com.challenge.transactions.entity;

import static java.util.Arrays.stream;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationType {

	A_VISTA( 1, "COMPRA A VISTA" ),
	PARCELADA( 2, "COMPRA PARCELADA" ),
	SAQUE( 3, "SAQUE" ),
	PAGAMENTO( 4, "PAGAMENTO" );

	private Integer id;
	private String description;

	public static Optional<OperationType> getById(Long id) {
		return stream( values() )
				.filter( value -> value.getId().equals( id ) )
				.findFirst();

	}

}
