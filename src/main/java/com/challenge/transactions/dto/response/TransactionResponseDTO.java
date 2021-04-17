package com.challenge.transactions.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

	private Long accountId;
	private Long operationTypeId;
	private BigDecimal amount;
	private String eventDate;

}
