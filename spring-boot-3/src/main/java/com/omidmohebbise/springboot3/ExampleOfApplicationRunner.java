package com.omidmohebbise.springboot3;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.StopWatch;

public class ExampleOfApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.println("Non-option arguments: " + args.getNonOptionArgs());
        System.out.println("Option names: " + args.getOptionNames());
        System.out.println("Option values: " + args.getOptionValues("my-option"));
        System.out.println("SourceArgs: " + args.getSourceArgs().length);
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds() / 100);
    }
}
