package com.challenge.transactions.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	private LocalDateTime eventDate;

}
