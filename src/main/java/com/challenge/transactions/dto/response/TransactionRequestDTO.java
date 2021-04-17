package com.challenge.transactions.dto.response;

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
	private Long operationTypeId;
	private String amount;

}
