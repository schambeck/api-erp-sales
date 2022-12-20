package com.schambeck.erp.sales.app.dataprovider;

import com.schambeck.erp.sales.app.dataprovider.entity.OrderEntity;
import com.schambeck.erp.sales.app.dataprovider.mapper.OrderMapper;
import com.schambeck.erp.sales.core.dataprovider.OrderRepository;
import com.schambeck.erp.sales.core.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.schambeck.erp.sales.app.dataprovider.entity.StatusOrder.CLOSED;

@Repository
@RequiredArgsConstructor
public class OrderGateway implements OrderRepository {
	private final JpaOrderRepository jpaRepository;

	@Override
	@Transactional
	public void close(UUID id) {
		jpaRepository.updateStatus(id, CLOSED);
	}

	@Override
	@Transactional
	public Order create(Order order) {
		OrderEntity entity = OrderMapper.INSTANCE.toEntity(order);
		OrderEntity created = jpaRepository.save(entity);
		return OrderMapper.INSTANCE.toDomain(created);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Order> findById(UUID id) {
		return jpaRepository.findById(id)
				.map(OrderMapper.INSTANCE::toDomain);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findAll() {
		return OrderMapper.INSTANCE.toDomain(jpaRepository.findAll());
	}
}
