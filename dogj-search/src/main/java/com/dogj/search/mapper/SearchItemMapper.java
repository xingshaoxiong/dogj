package com.dogj.search.mapper;

import com.dogj.search.bean.SearchItem;

import java.util.List;

public interface SearchItemMapper {
    List<SearchItem> getItemList();
    SearchItem getItemById(long itemId);
}
