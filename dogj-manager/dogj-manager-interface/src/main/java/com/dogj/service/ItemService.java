package com.dogj.service;

import com.dogj.common.pojo.DogjResult;
import com.dogj.pojo.DogjItem;
import com.github.pagehelper.PageInfo;

public interface ItemService {
    DogjItem getItemById(long itemId);
    PageInfo<DogjItem> getItemList(int page, int rows);
    DogjResult addItem(DogjItem item, String desc);
}
