package com.jurik99;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigDecimal;

@Getter
@Setter
// To initialize some data in the database, I've created (resources/data.sql) file
@Entity
public class ExchangeValue {

	@Id
	private long id;

	@Column(name = "currency_from") // changing name of this column as "from" is a reserved keyword in the SQL
	private String from;

	@Column(name = "currency_to")
	private String to;

	private BigDecimal conversionMultiple;
	private int port;

	public ExchangeValue() {
		// for JPA
	}

	ExchangeValue(final long id, final String from, final String to, final BigDecimal conversionMultiple) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}
}
