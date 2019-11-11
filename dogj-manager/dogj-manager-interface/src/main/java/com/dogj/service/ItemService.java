package com.dogj.service;

import com.dogj.common.pojo.DogjResult;
import com.dogj.pojo.DogjItem;

import java.util.List;

public interface ItemService {
    DogjItem getItemById(long itemId);
    List<DogjItem> getItemList(int page, int rows);
    DogjResult addItem(DogjItem item, String desc);
}
