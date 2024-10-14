package com.omidmohebbise.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    public static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @GetMapping("/info")
    public String logInfo(){
        log.info("logging info");
        return "info";
    }

    @GetMapping("/debug")
    public String logDebug(){
        log.debug("logging debug");
        return "debug";
    }

    @GetMapping("/error")
    public String logError(){
        log.error("logging error");
        return "error";
    }

    @GetMapping("/trace")
    public String logTrace(){
        log.trace("logging trace");
        return "trace";
    }

}
