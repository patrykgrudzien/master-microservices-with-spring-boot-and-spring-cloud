package com.jurik99;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

	private final Environment environment;

	public CurrencyExchangeController(final Environment environment) {
		this.environment = environment;
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable("from") final String from,
	                                           @PathVariable("to") final String to) {

		final String port = Optional.ofNullable(environment.getProperty("server.port"))
		                            .orElseThrow(() -> new RuntimeException("Cannot find specified property!"));

		final ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65L));
		exchangeValue.setPort(Integer.parseInt(port));
		return exchangeValue;
	}
}
