package com.coviam.project.SpringProjectOrder.orderRepository;

import com.coviam.project.SpringProjectOrder.orderDTO.OrderDTO;
import com.coviam.project.SpringProjectOrder.orderEntity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity,String> {

    public List<OrderEntity> findByUserid(String userid);

}
