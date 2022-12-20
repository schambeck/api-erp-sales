package com.schambeck.erp.sales.core.usecase.generator.impl;

import com.schambeck.erp.sales.core.usecase.generator.IdGenerator;

import javax.inject.Named;
import java.util.UUID;

@Named
class UuidGenerator implements IdGenerator {
	@Override
	public UUID generate() {
		return UUID.randomUUID();
	}
}
