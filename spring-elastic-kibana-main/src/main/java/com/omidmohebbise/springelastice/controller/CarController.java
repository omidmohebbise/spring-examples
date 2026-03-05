package com.omidmohebbise.springelastice.controller;

import com.omidmohebbise.springelastice.model.CarModel;
import com.omidmohebbise.springelastice.service.CarSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarSearchService carService;

    @PostMapping
    public void addCar(@RequestBody CarModel car) {
        carService.add(car);
    }

    @GetMapping
    public Iterable<CarModel> findAll() {
        return carService.findAll();
    }
    @GetMapping("/{searchedText}")
    public List<CarModel> search(@PathVariable String searchedText) {
        return carService.searchByModel(searchedText).getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
