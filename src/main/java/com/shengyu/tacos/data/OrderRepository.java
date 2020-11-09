package com.shengyu.tacos.data;

import com.shengyu.tacos.Order;
import org.springframework.data.repository.CrudRepository;

;

public interface OrderRepository extends CrudRepository<Order, Long> {
//    Order save(Order order);
   /* List<Order> findByDeliveryZip(String deliveryZip);
    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate
    );*/
}
