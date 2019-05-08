package com.jurik99;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// (name) is the name of the microservice specified in application.yml
// for now the important attribute is (url)
// (name) will be much more useful when we start using (Ribbon)
@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {

	// we have to define a method to talk to (currency-exchange-service)
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") final String from,
	                                             @PathVariable("to") final String to);
}
