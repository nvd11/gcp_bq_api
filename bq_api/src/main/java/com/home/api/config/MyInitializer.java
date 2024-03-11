package com.home.api.config;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@EnableAsync
public class MyInitializer {

    @PostConstruct
    public void init() {
        log.info("Application started...");
        //setProxy();
        printProxy();
    }

    private void setProxy() {
        System.setProperty("https.proxyHost", "10.0.1.223");
        System.setProperty("https.proxyPort", "7890");
    }

    private void printProxy(){
       log.info("https.proxyHost: " + System.getProperty("https.proxyHost"));
       log.info("https.proxyPort: " + System.getProperty("https.proxyPort"));
    }
}
