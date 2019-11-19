package com.dogj.order.pojo;

import com.dogj.pojo.DogjOrder;
import com.dogj.pojo.DogjOrderItem;
import com.dogj.pojo.DogjOrderShipping;

import java.util.List;

public class OrderInfo extends DogjOrder {
    private List<DogjOrderItem> orderItems;
    private DogjOrderShipping orderShipping;

    public List<DogjOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<DogjOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public DogjOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(DogjOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
