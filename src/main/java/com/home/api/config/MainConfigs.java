package com.home.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MainConfigs {
    @Bean("hostname")
    public String hostname() {
        return getHostName();
    }

    private String getHostName() {
        log.info("MainConfigs: getHostName ...");
        String hostName = "unknown";
        try {
            hostName = java.net.InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            log.error("Error in getting hostname...", e);
        }
        return hostName;
    }
}
