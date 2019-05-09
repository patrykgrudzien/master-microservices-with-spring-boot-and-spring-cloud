package com.jurik99;

import lombok.extern.log4j.Log4j2;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Log4j2
@RestController
public class CurrencyExchangeController {

	private final Environment environment;
	private final ExchangeValueRepository repository;

	public CurrencyExchangeController(final Environment environment, final ExchangeValueRepository repository) {
		this.environment = environment;
		this.repository = repository;
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable("from") final String from,
	                                           @PathVariable("to") final String to) {

		final String port = Optional.ofNullable(environment.getProperty("server.port"))
		                            .orElseThrow(() -> new RuntimeException("Cannot find specified property!"));

		final ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(port));

		log.info("{}", exchangeValue);

		return exchangeValue;
	}
}
