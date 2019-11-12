package com.dogj.controller;

import com.dogj.pojo.DogjItem;
import com.dogj.service.ItemService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public DogjItem getItemById(@PathVariable Long itemId) {
        DogjItem dogjItem = itemService.getItemById(itemId);
        return dogjItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public PageInfo<DogjItem> getItemList(Integer page, Integer rows) {
        PageInfo<DogjItem> pageInfo = itemService.getItemList(page, rows);
        return pageInfo;
    }
}
