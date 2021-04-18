package com.challenge.transactions.mapper;

import java.math.BigDecimal;

public class NegativeAmountMapper implements AmountMapper{

	@Override
	public BigDecimal map(BigDecimal amount) {
		return amount.abs().negate();
	}
}
