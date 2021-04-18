package com.challenge.transactions.configuration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.context.annotation.Configuration;

import com.challenge.transactions.entity.OperationType;

@Configuration
@Converter(autoApply = true)
public class OperationTypeConverter implements AttributeConverter<OperationType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(OperationType nodeType) {
		return nodeType.getId();
	}

	@Override
	public OperationType convertToEntityAttribute(Integer dbData) {
		for (OperationType opType : OperationType.values()) {
			if (opType.getId().equals( dbData )) {
				return opType;
			}
		}

		throw new IllegalArgumentException( "Unknown database value:" + dbData );
	}
}