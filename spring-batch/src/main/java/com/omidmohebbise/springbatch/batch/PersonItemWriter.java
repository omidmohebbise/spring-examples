package com.omidmohebbise.springbatch.batch;

import com.omidmohebbise.springbatch.domain.Person;
import com.omidmohebbise.springbatch.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PersonItemWriter<T extends Person> implements ItemWriter<Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    private final PersonService personService;

    public PersonItemWriter(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        personService.saveAll((List<Person>) chunk.getItems());
    }
}
