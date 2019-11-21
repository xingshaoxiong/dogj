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
import java.util.Set;

public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private DogjSeckillGoodsDao seckillGoodsDao;

    @Autowired
    private JedisClient jedisClient;

    @Value("${SECKILL_GOODS_SESSION}")
    private String SECKILL_GOODS_SESSION;

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
        DogjSeckillGoods goods = getSeckillGoodsById(id);
        goods.setStockCount(Integer.valueOf(jedisClient.get(id.toString())));
        return goods;
    }

    @Override
    public List<DogjSeckillGoods> getGoodsListFromRedis() {
        Set<String> keySet = jedisClient.getKeys(SECKILL_GOODS_SESSION + "*");
        List<DogjSeckillGoods> list = new ArrayList<>();
        if (keySet.isEmpty()) {
            DogjSeckillGoodsQuery query = new DogjSeckillGoodsQuery();
            DogjSeckillGoodsQuery.Criteria criteria = query.createCriteria();
            criteria.andStockCountGreaterThan(-1);
            criteria.andStartTimeLessThanOrEqualTo(new Date());
            criteria.andEndTimeGreaterThanOrEqualTo(new Date());
            list = seckillGoodsDao.selectByExample(query);
            for (DogjSeckillGoods goods : list) {
                jedisClient.set(SECKILL_GOODS_SESSION + ":" + goods.getId().toString(), goods.getStockCount().toString());
            }
        } else {
            for (String id : keySet) {
                DogjSeckillGoods goods = getGoodsFromRedis(Long.parseLong(id));
                if (goods.getStockCount() > 0) {
                    list.add(goods);
                }
            }
        }
        return list;
    }
}
