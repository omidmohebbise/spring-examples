package com.omidmohebbise.springelastice.service;

import com.omidmohebbise.springelastice.model.CarModel;
import com.omidmohebbise.springelastice.repository.CarElasticRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarSearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final CarElasticRepository repository;

    public void add(CarModel car) {
        repository.save(car);
    }

    public Iterable<CarModel> findAll() {
        return repository.findAll();
    }


    public SearchHits<CarModel> searchByModel(String searchedText) {
        // Get all item with given name
        Criteria criteria = new Criteria("model").contains(searchedText)
                .or("brand").contains(searchedText);
        Query searchQuery = new CriteriaQuery(criteria);
        return elasticsearchOperations.search(searchQuery, CarModel.class);
    }
}
