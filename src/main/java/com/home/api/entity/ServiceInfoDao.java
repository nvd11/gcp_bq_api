package com.home.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfoDao {
    private String app;
    private String version;
    private String description;
    private String hostname;
    private String dbUrl;
}
