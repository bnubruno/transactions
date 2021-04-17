package com.challenge.transactions.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.challenge.transactions.dto.response.TransactionRequestDTO;
import com.challenge.transactions.entity.Account;
import com.challenge.transactions.entity.OperationType;
import com.challenge.transactions.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "account", source = "account")
	@Mapping(target = "amount", source = "dto.amount")
	@Mapping(target = "eventDate", ignore = true)
	@Mapping(target = "operationType", source = "operationType")
	Transaction toEntity(TransactionRequestDTO dto, Account account, OperationType operationType);

}
