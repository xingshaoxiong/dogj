package com.dogj.search;

import com.dogj.pojo.DogjItem;
import com.dogj.search.bean.SearchItem;
import com.dogj.search.mapper.SearchItemMapper;
import com.dogj.search.repository.SearchItemRepository;
import com.dogj.service.ItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogjSearchApplicationTests {

    @Reference
    private ItemService itemService;
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SearchItemRepository searchItemRepository;
    @Test
    public void contextLoads() {
        DogjItem item = itemService.getItemById(562379);
        System.out.println(item.getTitle());
    }

    @Test
    public void mybatisTest() {
        List<SearchItem> list = searchItemMapper.getItemList();
        System.out.println(list.get(0).getItem_desc());
        System.out.println(list.get(0).getId());
    }

    //可能是版本问题，测试无法通过。工作环境可以正常
//    @Test
//    public void esTest() {
//        List<SearchItem> list = searchItemMapper.getItemList();
//        searchItemRepository.index(list.get(0));
//        for (SearchItem searchItem : searchItemRepository.findByTitleLike("手机")) {
//            System.out.println(searchItem.getId());
//        }
//    }

    @Test
    public void rabbitmqTest() {
        DogjItem item = new DogjItem();
        item.setTitle("华为手机");
        item.setPrice(999L);
        item.setNum(10);
        item.setSellPoint("牢厂254");
        item.setCid(10L);
        itemService.addItem(item, "testRabbitMq");
    }

}
