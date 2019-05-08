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

	private final CurrencyExchangeServiceProxy proxy;

	public CurrencyConversionController(final CurrencyExchangeServiceProxy proxy) {
		this.proxy = proxy;
	}

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

	/**
	 * I'll be using (Feign REST Client) as it allows to:
	 * 1) Write much less and easier code comparing to {@link org.springframework.web.client.RestTemplate}
	 * 2) Has integration with Client Side Load Balancing Framework (Ribbon)
	 */
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable final String from,
	                                                   @PathVariable final String to,
	                                                   @PathVariable final BigDecimal quantity) {

		final CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		final BigDecimal conversionMultiple = response.getConversionMultiple();
		return new CurrencyConversionBean(response.getId(), response.getFrom(), response.getTo(),
		                                  conversionMultiple, quantity, quantity.multiply(conversionMultiple),
		                                  response.getPort());
	}
}
