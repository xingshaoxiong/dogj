package com.dogj.service.impl;

import com.dogj.dao.DogjItemCatDao;
import com.dogj.pojo.DogjItemCat;
import com.dogj.pojo.DogjItemCatQuery;
import com.dogj.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private DogjItemCatDao dogjItemCatDao;

    @Override
    public List<DogjItemCat> getItemCatList(long parentId) {
        DogjItemCatQuery query = new DogjItemCatQuery();
        DogjItemCatQuery.Criteria criteria = query.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<DogjItemCat> list = dogjItemCatDao.selectByExample(query);
        return list;
    }
}
