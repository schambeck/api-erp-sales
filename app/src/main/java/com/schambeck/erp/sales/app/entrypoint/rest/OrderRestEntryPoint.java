package com.schambeck.erp.sales.app.entrypoint.rest;

import com.schambeck.erp.sales.app.entrypoint.OrderEntryPoint;
import com.schambeck.erp.sales.app.entrypoint.controller.OrderController;
import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestEntryPoint implements OrderEntryPoint {
	private final OrderController controller;

	@Override
	@PostMapping
	public OrderWeb create(@RequestBody @Valid OrderWeb web) {
		return controller.create(web);
	}

	@Override
	@PostMapping("{id}/close")
	public void close(@PathVariable("id") UUID id) {
		controller.close(id);
	}

	@Override
	@GetMapping("{id}")
	public OrderWeb findById(@PathVariable("id") UUID id) {
		return controller.findById(id);
	}

	@Override
	@GetMapping
	public List<OrderWeb> findAll() {
		return controller.findAll();
	}
}
