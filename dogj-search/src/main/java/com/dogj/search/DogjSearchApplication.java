package com.dogj.search;

import com.dogj.search.bean.SearchItem;
import com.dogj.search.mapper.SearchItemMapper;
import com.dogj.search.repository.SearchItemRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@MapperScan(value = "com.dogj.search.mapper")
@SpringBootApplication
public class DogjSearchApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        ConfigurableApplicationContext context =SpringApplication.run(DogjSearchApplication.class, args);
        //做测试用，test文件中无法通过，这边可以
        SearchItemRepository searchItemRepository =context.getBean(SearchItemRepository.class);
        SearchItemMapper searchItemMapper = context.getBean(SearchItemMapper.class);
        List<SearchItem> list = searchItemMapper.getItemList();
        searchItemRepository.index(list.get(0));
        for (SearchItem searchItem : searchItemRepository.findByTitleLike("手机")) {
            System.out.println(searchItem.getId());
        }
    }

}
