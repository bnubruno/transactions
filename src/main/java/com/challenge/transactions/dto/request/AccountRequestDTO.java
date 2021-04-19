package com.challenge.transactions.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

	@NotNull(message = "Document number must be not null")
	@JsonProperty("document_number")
	private Long documentNumber;

}
