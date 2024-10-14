package com.omidmohebbise.liquibase.service;

import com.omidmohebbise.liquibase.entity.Person;
import com.omidmohebbise.liquibase.repository.PersonJpaRepository;
import com.omidmohebbise.liquibase.service.dto.PersonFilterDto;
import com.omidmohebbise.liquibase.service.dto.SortDto;
import com.omidmohebbise.liquibase.service.filter.PersonFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPerson {

    private PersonJpaRepository personJpaRepository;

    public ListPerson(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    public List<Person> list(int page , int size , PersonFilterDto personFilterDto){
        Pageable paging =
                personFilterDto.getSorts() == null ? PageRequest.of(page, size)
                        : PageRequest.of(page, size, createSortStatement(personFilterDto.getSorts()));
        Specification<Person> specification = null;
        if(personFilterDto.getFirstname()!= null && !personFilterDto.getFirstname().isBlank()){
            Specification  temp = PersonFilter.firstname(personFilterDto.getFirstname());
            specification = specification == null ? temp : specification.and(temp);
        }
        if(personFilterDto.getLastname()!= null && !personFilterDto.getLastname().isBlank()){
            Specification  temp = PersonFilter.lastname(personFilterDto.getLastname());
            specification = specification == null ? temp : specification.and(temp);
        }
        if(personFilterDto.getAge()!= null){
            Specification  temp = PersonFilter.age(personFilterDto.getAge());
            specification = specification == null ? temp : specification.and(temp);
        }
        return personJpaRepository.findAll(specification,paging).getContent();
    }

    protected Sort createSortStatement(List<SortDto> filters) {

        if (filters == null || filters.isEmpty())
            return null;
        Sort sorts = null;
        for (SortDto x : filters) {
            sorts = sorts == null ? Sort.by(getDirection(x), x.getField()) : sorts.and(Sort.by(getDirection(x), x.getField()));
        }

        return sorts;
    }

    private Sort.Direction getDirection(SortDto x) {
        return x.getField() == null || x.getField().compareToIgnoreCase("asc") == 0 ? Sort.Direction.ASC : Sort.Direction.DESC;
    }



}
