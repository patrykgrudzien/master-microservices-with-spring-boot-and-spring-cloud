package com.jurik99;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable final String from,
	                                              @PathVariable final String to,
	                                              @PathVariable final BigDecimal quantity) {
		return new CurrencyConversionBean(1L, from, to, BigDecimal.ONE, quantity, quantity, 0);
	}
}
