package com.dogj.search.service.impl;

import com.dogj.common.pojo.DogjResult;
import com.dogj.search.bean.SearchItem;
import com.dogj.search.mapper.SearchItemMapper;
import com.dogj.search.repository.SearchItemRepository;
import com.dogj.search.service.SearchItemService;
import com.dogj.service.ItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Reference
    private ItemService itemService;

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SearchItemRepository searchItemRepository;

    @Override
    public DogjResult importItemsToIndex() {
        List<SearchItem> itemList = searchItemMapper.getItemList();
        for (SearchItem searchItem : itemList) {
            searchItemRepository.index(searchItem);
        }
        return DogjResult.ok();
    }
}
