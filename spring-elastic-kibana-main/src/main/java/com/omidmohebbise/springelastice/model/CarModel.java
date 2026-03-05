package com.omidmohebbise.springelastice.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "car")
@Data
public class CarModel {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "model")
    private String model;

    @Field(type = FieldType.Text, name = "brand")
    private String brand;

    @Field(type = FieldType.Integer, name = "year")
    private int year;

    @Field(type = FieldType.Double, name = "price")
    private double price;


}
