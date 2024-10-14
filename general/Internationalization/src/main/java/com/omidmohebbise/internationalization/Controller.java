package com.omidmohebbise.internationalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class Controller {
    @Autowired MessageSource messageSource;


    @Autowired RequestHeader requestHeader;

    @GetMapping("/{key}")
    public String getMessageAccordingToHeaderRequest(@PathVariable(name = "key") String key){
        return messageSource.getMessage("Hello",null, Locale.forLanguageTag(getLanguage()));
    }

    @GetMapping("/test")
    public String test(){
        return "Hora";
    }

    private String getLanguage(){
        return requestHeader.getAcceptLanguage();
    }
}

