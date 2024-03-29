package com.home.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class K8SConfig {

    @Value("${podLogTableName}")
    private String podLogTable;

    @Value("${k8sDataSet}")
    private String k8sDataSet;

    @Bean
    public String podLogTable() {
        return podLogTable;
    }

    @Bean
    public String k8sDataSet() {
        return k8sDataSet;
    }
}
