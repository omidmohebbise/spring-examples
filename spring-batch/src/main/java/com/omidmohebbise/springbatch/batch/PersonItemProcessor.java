package com.omidmohebbise.springbatch.batch;

import com.omidmohebbise.springbatch.domain.Person;
import org.springframework.batch.item.ItemProcessor;


public class PersonItemProcessor implements ItemProcessor<PersonDTO, Person> {

    @Override
    public Person process(final PersonDTO item) throws Exception {
        var person = new Person();
        person.setFirstName(item.getFirstName());
        person.setLastName(item.getLastName());
        return person;
    }
}
