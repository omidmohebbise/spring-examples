package com.omidmohebbise.springbatch;

import com.omidmohebbise.springbatch.batch.BatchImportPersonService;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchApplication implements CommandLineRunner {


    private final JobLauncher jobLauncher;
    private final BatchImportPersonService batchImportPersonService;

    public SpringBatchApplication(JobLauncher jobLauncher, BatchImportPersonService batchImportPersonService) {
        this.jobLauncher = jobLauncher;
        this.batchImportPersonService = batchImportPersonService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(batchImportPersonService.importPersonsJob(), new JobParameters());
    }
}
