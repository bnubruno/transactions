package com.challenge.transactions.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

	@NotNull(message = "Account Id must be not null")
	@JsonProperty("account_id")
	private Long accountId;

	@NotNull(message = "Operation Type must be not null")
	@JsonProperty("operation_type_id")
	private Integer operationTypeId;

	@NotNull
	private BigDecimal amount;

}
