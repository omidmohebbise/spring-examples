package com.omidmohebbise.springmongo.person;

import java.time.Instant;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "people")
@CompoundIndex(name = "idx_nationalId_passportNumber", def = "{'nationalId': 1, 'passportNumber': 1}")
public class Person {

    @Id
    private String id;

    @Indexed(name = "idx_nationalId")
    private String nationalId;

    @Indexed(name = "idx_passportNumber")
    private String passportNumber;

    private String firstName;

    private String lastName;

    private Integer age;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;


    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

}
