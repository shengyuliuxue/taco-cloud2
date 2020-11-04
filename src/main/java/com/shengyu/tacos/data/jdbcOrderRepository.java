package com.shengyu.tacos.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shengyu.tacos.Order;
import com.shengyu.tacos.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class jdbcOrderRepository implements OrderRepository{

    private  ObjectMapper objectMapper;
    private  SimpleJdbcInsert orderTacoInserter;
    private  SimpleJdbcInsert orderInserter;

    @Autowired
    public jdbcOrderRepository(JdbcTemplate jdbc){
        this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDeatils(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos){
            saveTacoToOrder(taco, orderId);
        }
        return order;
    }

    private long saveOrderDeatils(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object>values =
            objectMapper.convertValue(order, Map.class);
        values.put("placeAt", order.getPlacedAt());
        long orderId =
                orderInserter.executeAndReturnKey(values).longValue();
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId){
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }
}
