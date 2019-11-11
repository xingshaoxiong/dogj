package com.dogj.service;

import com.dogj.pojo.DogjItemCat;

import java.util.List;

public interface ItemCatService {
    List<DogjItemCat> getItemCatList(long parentId);
}
