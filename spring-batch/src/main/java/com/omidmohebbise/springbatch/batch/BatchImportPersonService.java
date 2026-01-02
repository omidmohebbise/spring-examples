package com.omidmohebbise.springbatch.batch;

import com.omidmohebbise.springbatch.domain.Person;
import com.omidmohebbise.springbatch.service.PersonService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchImportPersonService {

    private final PersonService personService;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchImportPersonService(PersonService personService, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.personService = personService;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public FlatFileItemReader<PersonDTO> reader() {
        return new FlatFileItemReaderBuilder<PersonDTO>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names("firstName", "lastName")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<PersonDTO>() {{
                    setTargetType(PersonDTO.class);
                }})
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public PersonItemWriter<Person> writer() {
        return new PersonItemWriter(personService);
    }

    public Job importPersonsJob() {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1(writer()))
                .end()
                .build();
    }

    @Bean
    public Step step1(PersonItemWriter<Person> writer) {
        return new StepBuilder("step1", jobRepository)
                .<PersonDTO, Person>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
