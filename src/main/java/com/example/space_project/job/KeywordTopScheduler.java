package com.example.space_project.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class KeywordTopScheduler {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private KeywordTopJobConfig keywordTopJobConfig;

    @Scheduled(cron = "0 */5 * * * *")
    public void runJob() {
        Map<String, JobParameter> configMap = new HashMap<>();
        configMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(configMap);

        try {
            jobLauncher.run(keywordTopJobConfig.keywordTop(), jobParameters);
        } catch  (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                  | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {

            log.error(e.getMessage());
        }
    }
}
