package com.omidmohebbise.springthroughput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TomcatConnectionMonitor {
    private static final Logger log = LoggerFactory.getLogger(TomcatConnectionMonitor.class);
    private final WebServerApplicationContext webServerApplicationContext;

    public TomcatConnectionMonitor(WebServerApplicationContext webServerApplicationContext) {
        this.webServerApplicationContext = webServerApplicationContext;
    }

}
