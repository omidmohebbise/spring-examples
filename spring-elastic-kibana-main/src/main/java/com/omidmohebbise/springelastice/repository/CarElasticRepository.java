package com.omidmohebbise.springelastice.repository;

import com.omidmohebbise.springelastice.model.CarModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CarElasticRepository extends ElasticsearchRepository<CarModel, String> {
}
