package com.jurik99;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable("from") final String from,
	                                           @PathVariable("to") final String to) {
		return new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65L));
	}
}
