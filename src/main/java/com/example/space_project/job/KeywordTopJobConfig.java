package com.example.space_project.job;

import com.example.space_project.domain.Top10;
import com.example.space_project.service.Top10Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KeywordTopJobConfig {

    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음
    private final EntityManagerFactoryBuilder entityManagerFactory;

    private final Top10Service top10Service;

    @Bean
    public Job keywordTop() {
        return jobBuilderFactory.get("simpleJob")
                .start(step(null))
                .build();
    }

    @Bean
    @JobScope
    public Step step(@Value("#{jobParameters[time]}") String time){
        return stepBuilderFactory.get("step")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> Search Keyword");
                    top10Service.saveTop10Keyword();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
