package com.jurik99;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// (name) is the name of the microservice specified in application.yml
// for now the important attribute is (url)
// (name) will be much more useful when we start using (Ribbon)
//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")

// having @RibbonClient - I don't need to have @FeignClient with "url" attribute above anymore
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	// we have to define a method to talk to (currency-exchange-service)
	// @GetMapping("/currency-exchange/from/{from}/to/{to}")

	// http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/1000 (it will go through API Gateway)
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") final String from,
	                                             @PathVariable("to") final String to);
}
