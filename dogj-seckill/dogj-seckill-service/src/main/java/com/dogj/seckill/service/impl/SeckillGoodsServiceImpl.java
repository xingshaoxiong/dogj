package com.dogj.seckill.service.impl;

import com.dogj.common.pojo.DogjResult;
import com.dogj.common.utils.JsonUtil;
import com.dogj.dao.DogjSeckillGoodsDao;
import com.dogj.jedis.JedisClient;
import com.dogj.pojo.DogjSeckillGoods;
import com.dogj.pojo.DogjSeckillGoodsQuery;
import com.dogj.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private DogjSeckillGoodsDao seckillGoodsDao;

    @Autowired
    private JedisClient jedisClient;

    @Value("${SECKILL_SESSION}")
    private String SECKILL_SESSION;

    @Override
    public List<DogjSeckillGoods> getSeckillGoodsList() {
        DogjSeckillGoodsQuery query = new DogjSeckillGoodsQuery();
        return seckillGoodsDao.selectByExample(query);
    }

    @Override
    public DogjSeckillGoods getSeckillGoodsById(Long id) {
        return seckillGoodsDao.selectByPrimaryKey(id);
    }

    @Override
    public DogjResult addSeckillGoods(DogjSeckillGoods goods) {
        seckillGoodsDao.insert(goods);
        return DogjResult.ok();
    }

    @Override
    public DogjResult updateSeckillGoods(DogjSeckillGoods goods) {
        seckillGoodsDao.updateByPrimaryKey(goods);
        return DogjResult.ok();
    }

    @Override
    public DogjResult deleteSeckillGoods(Long id) {
        seckillGoodsDao.deleteByPrimaryKey(id);
        return DogjResult.ok();
    }

    @Override
    public DogjSeckillGoods getGoodsFromRedis(Long id) {
        return JsonUtil.jsonToPojo(jedisClient.hget(SECKILL_SESSION + ":seckillGoods", id.toString()), DogjSeckillGoods.class);
    }

    @Override
    public List<DogjSeckillGoods> getGoodsListFromRedis() {
        List<DogjSeckillGoods> list = new ArrayList<>();
        if (!jedisClient.exists(SECKILL_SESSION + ":" + "seckillGoods")) {
            DogjSeckillGoodsQuery query = new DogjSeckillGoodsQuery();
            DogjSeckillGoodsQuery.Criteria criteria = query.createCriteria();
            criteria.andStockCountGreaterThan(0);
            criteria.andStartTimeLessThanOrEqualTo(new Date());
            criteria.andEndTimeGreaterThanOrEqualTo(new Date());
            list = seckillGoodsDao.selectByExample(query);
            for (DogjSeckillGoods goods : list) {
                jedisClient.hset(SECKILL_SESSION + ":seckillGoods", goods.getId().toString(), JsonUtil.objectToJson(goods));
            }
        } else {
            List<String> redisList = jedisClient.hgetAll(SECKILL_SESSION + ":seckillGoods");
            for (String s : redisList) {
                list.add(JsonUtil.jsonToPojo(s, DogjSeckillGoods.class));
            }
        }
        return list;
    }
}
