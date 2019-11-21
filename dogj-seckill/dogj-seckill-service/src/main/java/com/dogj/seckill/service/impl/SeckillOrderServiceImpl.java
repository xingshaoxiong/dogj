package com.dogj.seckill.service.impl;

import com.dogj.common.pojo.DogjResult;
import com.dogj.common.utils.IDUtil;
import com.dogj.common.utils.JsonUtil;
import com.dogj.dao.DogjSeckillGoodsDao;
import com.dogj.dao.DogjSeckillOrderDao;
import com.dogj.jedis.JedisClient;
import com.dogj.pojo.DogjSeckillGoods;
import com.dogj.pojo.DogjSeckillOrder;
import com.dogj.seckill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private DogjSeckillOrderDao seckillOrderDao;

    @Autowired
    private DogjSeckillGoodsDao seckillGoodsDao;

    @Value("${SECKILL_GOODS_SESSION}")
    private String SECKILL_GOODS_SESSION;

    @Value("${SECKILL_ORDER_SESSION}")
    private String SECKILL_ORDER_SESSION;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<DogjSeckillOrder> getSeckillOrderList() {
        return seckillOrderDao.selectByExample(null);
    }

    @Override
    public DogjSeckillOrder getSeckillOrderById(Long id) {
        return seckillOrderDao.selectByPrimaryKey(id);
    }

    @Override
    public DogjResult addSeckillOrder(DogjSeckillOrder goods) {
        seckillOrderDao.insert(goods);
        return DogjResult.ok();
    }

    @Override
    public DogjResult updateSeckillOrder(DogjSeckillOrder goods) {
        seckillOrderDao.updateByPrimaryKey(goods);
        return DogjResult.ok();
    }

    @Override
    public DogjResult deleteSeckillOrder(Long id) {
        seckillOrderDao.deleteByPrimaryKey(id);
        return DogjResult.ok();
    }

    @Override
    public DogjResult submitOrder(Long seckillId, Long userId) {
        String redisId = SECKILL_GOODS_SESSION + ":" + seckillId.toString();
        Integer count = Integer.valueOf(jedisClient.get(redisId));
        if (count <= 0) {
            return DogjResult.build(404, "商品已经被抢光");
        }

        if (jedisClient.decr(redisId) < 0) {
            jedisClient.set(redisId, "0");
            DogjSeckillGoods goods = seckillGoodsDao.selectByPrimaryKey(seckillId);
            goods.setStockCount(0);
            seckillGoodsDao.updateByPrimaryKey(goods);
            return DogjResult.build(404, "商品已经被抢光");
        }
        DogjSeckillOrder order = new DogjSeckillOrder();
        order.setId(IDUtil.genItemId());
        order.setSeckillId(seckillId);
        order.setUserId(userId.toString());
        order.setStatus("0");
        jedisClient.set(SECKILL_ORDER_SESSION + ":" + userId.toString(), JsonUtil.objectToJson(order));
        return DogjResult.ok(order);
    }

    @Override
    public DogjSeckillOrder getOrderFromRedisByUserId(Long id) {
        return null;
    }

    @Override
    public DogjResult saveOrderFromRedisToDb(String userId, Long orderId) {
        return null;
    }

    @Override
    public DogjResult deleteOrderFromRedis(String userId, Long orderId) {
        return null;
    }
}
