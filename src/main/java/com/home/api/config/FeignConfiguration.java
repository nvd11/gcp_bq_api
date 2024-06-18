package com.home.api.config;

import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import feign.Logger.Level;
public class FeignConfiguration {


    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder().client(new ApacheHttpClient());
    }


    @Bean
    public Level feignLogLevel(){
        return Level.BASIC;
    }
}