package com.dogj.seckill.service;

import com.dogj.common.pojo.DogjResult;
import com.dogj.pojo.DogjSeckillOrder;

import java.util.List;

public interface SeckillOrderService {
    List<DogjSeckillOrder> getSeckillOrderList();
    DogjSeckillOrder getSeckillOrderById(Long id);
    DogjResult addSeckillOrder(DogjSeckillOrder goods);
    DogjResult updateSeckillOrder(DogjSeckillOrder goods);
    DogjResult deleteSeckillOrder(Long id);
    DogjResult submitOrder(Long seckillId, Long userId);
    DogjSeckillOrder getOrderFromRedisByUserId(Long userId);
    DogjResult saveOrderFromRedisToDb(String userId, Long orderId);
    DogjResult deleteOrderFromRedis(String userId, Long orderId);
}
