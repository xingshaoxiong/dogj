package com.dogj.order.service.impl;

import com.dogj.common.pojo.DogjResult;
import com.dogj.dao.DogjOrderDao;
import com.dogj.dao.DogjOrderItemDao;
import com.dogj.dao.DogjOrderShippingDao;
import com.dogj.jedis.JedisClient;
import com.dogj.order.pojo.OrderInfo;
import com.dogj.order.service.OrderService;
import com.dogj.pojo.DogjOrderItem;
import com.dogj.pojo.DogjOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private DogjOrderDao orderDao;
    @Autowired
    private DogjOrderItemDao orderItemDao;
    @Autowired
    private DogjOrderShippingDao orderShippingDao;
    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;
    @Value("${ORDER_ID_BEGIN_VALUE}")
    private String ORDER_ID_BEGIN_VALUE;
    @Value("${ORDER_ITEM_ID_GEN_KEY}")
    private String ORDER_ITEM_ID_GEN_KEY;

    @Override
    public DogjResult createOrder(OrderInfo orderInfo) {
        if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
            jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_BEGIN_VALUE);
        }
        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
        orderInfo.setOrderId(orderId);
        orderInfo.setPostFee("0");
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderDao.insert(orderInfo);
        List<DogjOrderItem> orderItems = orderInfo.getOrderItems();
        for (DogjOrderItem dogjOrderItem : orderItems) {
            String oid = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY).toString();
            dogjOrderItem.setId(oid);
            dogjOrderItem.setOrderId(orderId);
            orderItemDao.insert(dogjOrderItem);
        }
        DogjOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingDao.insert(orderShipping);
        return DogjResult.ok(orderId);
    }
}
