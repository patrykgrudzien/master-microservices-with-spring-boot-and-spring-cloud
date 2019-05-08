package com.jurik99;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeValue {

	private long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
}
