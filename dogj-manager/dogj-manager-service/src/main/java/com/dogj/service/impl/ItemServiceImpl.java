package com.dogj.service.impl;

import com.dogj.common.pojo.DogjResult;
import com.dogj.common.utils.IDUtil;
import com.dogj.dao.DogjItemDao;
import com.dogj.dao.DogjItemDescDao;
import com.dogj.pojo.DogjItem;
import com.dogj.pojo.DogjItemDesc;
import com.dogj.pojo.DogjItemQuery;
import com.dogj.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private DogjItemDao dogjItemDao;
    @Autowired
    private DogjItemDescDao dogjItemDescDao;

    @Override
    public DogjItem getItemById(long itemId) {
        DogjItem item = dogjItemDao.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public PageInfo<DogjItem> getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        DogjItemQuery query = new DogjItemQuery();
        List<DogjItem> list = dogjItemDao.selectByExample(query);
        PageInfo<DogjItem> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public DogjResult addItem(DogjItem item, String desc) {
        long itemId = IDUtil.genItemId();
        item.setId(itemId);
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        dogjItemDao.insert(item);
        DogjItemDesc itemDesc = new DogjItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDesc.setCreated(new Date());
        dogjItemDescDao.insert(itemDesc);
        return DogjResult.ok();
    }
}
