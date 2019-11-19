package com.dogj.order.service;

import com.dogj.common.pojo.DogjResult;
import com.dogj.order.pojo.OrderInfo;

public interface OrderService {
    DogjResult createOrder(OrderInfo orderInfo);
}
