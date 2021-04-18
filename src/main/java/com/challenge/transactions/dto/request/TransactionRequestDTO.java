package com.challenge.transactions.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

	private Long accountId;
	private Integer operationTypeId;
	private BigDecimal amount;

}
