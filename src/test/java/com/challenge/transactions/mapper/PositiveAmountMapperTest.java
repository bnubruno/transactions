package com.challenge.transactions.mapper;

import static java.math.BigDecimal.TEN;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class PositiveAmountMapperTest {

	@Test
	public void whenPositiveAmountMapper() {
		BigDecimal result = new PositiveAmountMapper().map( TEN.abs().negate() );

		assertThat( result, Matchers.comparesEqualTo( TEN ) );
	}

}
