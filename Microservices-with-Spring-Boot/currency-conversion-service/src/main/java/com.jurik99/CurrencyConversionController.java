package com.jurik99;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CurrencyConversionController {

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable final String from,
	                                              @PathVariable final String to,
	                                              @PathVariable final BigDecimal quantity) {

		final Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		final ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
		final CurrencyConversionBean responseBody =
				Optional.ofNullable(responseEntity.getBody()).orElseThrow(() -> new RuntimeException("No body in the response entity!"));

		final BigDecimal conversionMultiple = responseBody.getConversionMultiple();
		return new CurrencyConversionBean(responseBody.getId(), responseBody.getFrom(), responseBody.getTo(),
		                                  conversionMultiple, quantity, quantity.multiply(conversionMultiple),
		                                  responseBody.getPort());
	}
}
