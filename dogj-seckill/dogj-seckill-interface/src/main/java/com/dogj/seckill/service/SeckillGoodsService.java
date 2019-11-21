package com.dogj.seckill.service;

import com.dogj.common.pojo.DogjResult;
import com.dogj.pojo.DogjSeckillGoods;

import java.util.List;

public interface SeckillGoodsService {
    List<DogjSeckillGoods> getSeckillGoodsList();

    DogjSeckillGoods getSeckillGoodsById(Long id);

    DogjResult addSeckillGoods(DogjSeckillGoods goods);

    DogjResult updateSeckillGoods(DogjSeckillGoods goods);

    DogjResult deleteSeckillGoods(Long id);

    DogjSeckillGoods getGoodsFromRedis(Long id);

    List<DogjSeckillGoods> getGoodsListFromRedis();
}
