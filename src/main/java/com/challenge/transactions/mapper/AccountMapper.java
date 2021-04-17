package com.challenge.transactions.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.challenge.transactions.dto.request.AccountRequestDTO;
import com.challenge.transactions.dto.response.AccountResponseDTO;
import com.challenge.transactions.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "documentNumber", source = "documentNumber")
	Account toEntity(AccountRequestDTO dto);

	@Mapping(target = "accountId", source = "id")
	@Mapping(target = "documentNumber", source = "documentNumber")
	AccountResponseDTO toDTO(Account dto);

}
