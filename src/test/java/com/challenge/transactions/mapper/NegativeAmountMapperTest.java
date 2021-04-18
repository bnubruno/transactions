package com.challenge.transactions.mapper;

import static java.math.BigDecimal.TEN;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class NegativeAmountMapperTest {

	@Test
	public void whenNegativeAmountMapper() {
		BigDecimal result = new NegativeAmountMapper().map( TEN );

		assertThat( result, Matchers.comparesEqualTo( TEN.abs().negate() ) );
	}

	@Test
	public void whenNegativeAmountMapperWithNegativeValue() {
		BigDecimal result = new NegativeAmountMapper().map( TEN.abs().negate() );

		assertThat( result, Matchers.comparesEqualTo( TEN.abs().negate() ) );
	}

}
