package com.dogj.search.repository;

import com.dogj.search.bean.SearchItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SearchItemRepository extends ElasticsearchRepository<SearchItem, Integer> {
    public List<SearchItem> findByTitleLike(String searchItemTitle);
}
