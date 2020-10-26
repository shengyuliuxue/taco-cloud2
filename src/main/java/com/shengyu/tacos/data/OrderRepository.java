package com.shengyu.tacos.data;

import com.shengyu.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
