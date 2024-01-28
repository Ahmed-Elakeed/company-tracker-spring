package com.study.companytracker.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import com.study.companytracker.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Configuration
@EnableBatchProcessing
public class PersonBatchConfiguration {

    private final StepBuilderFactory stepBuilderFactory;

    private final JobBuilderFactory jobBuilderFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PersonBatchConfiguration(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("input-data.csv"))
                .delimited()
                .delimiter("\t")
                .names("id", "name", "age")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
        return person -> {
            // You can perform any processing here if needed
            return person;
        };
    }

    @Bean
    public ItemWriter<Person> writer() {
        return items -> {
            // In a real-world scenario, you would typically use JdbcBatchItemWriter or JpaItemWriter
            // Here, we are just printing the items to the console for demonstration purposes
            for (Person person : items) {
                this.entityManager.merge(person);
            }
        };
    }

    @Bean
    public Step step1(ItemReader<Person> reader, ItemProcessor<Person, Person> processor, ItemWriter<Person> writer) {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

//    private static class CustomBeanWrapperFieldSetMapper<T> extends BeanWrapperFieldSetMapper<T> {
//
//        @Override
//        public T mapFieldSet(FieldSet fieldSet) throws BindException {
//
//            // Create a new instance of the target object (Person) and set the modified values
//            Person person = new Person();
//            person.setId(fieldSet.readString("id"));
//            person.setName(fieldSet.readString("name"));
//            person.setAge(fieldSet.readString("age"));
//
//            // Delegate to super to perform standard mapping
//            return super.mapFieldSet(fieldSet);
//        }
//    }
}
