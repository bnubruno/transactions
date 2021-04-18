package com.challenge.transactions.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

	@JsonProperty("account_id")
	private Long accountId;

	@JsonProperty("document_number")
	private Long documentNumber;

}
