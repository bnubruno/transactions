package com.challenge.transactions.dto.request;

import javax.validation.constraints.NotEmpty;
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
public class AccountRequestDTO {

	@NotNull(message = "Document number must be not null")
	@NotEmpty(message = "Document number must be not empty")
	@JsonProperty("document_number")
	private String documentNumber;

}
