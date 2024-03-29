package com.home.api.dao.impl;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.InsertAllRequest;
import com.google.cloud.bigquery.InsertAllResponse;
import com.home.api.dao.K8sDao;
import com.home.api.enums.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class K8sDaoImpl implements K8sDao {
    @Autowired
    private BigQuery bigQuery;

    @Autowired
    @Qualifier("podLogTable")
    private String podLogTable;

    @Autowired
    private String k8sDataSet;


    @Override
    public void insertPodLog(String podName, String nameSpace, String podVersion) throws Exception {
        log.info("PodName: " + podName + " Namespace: " + nameSpace + " PodVersion: " + podVersion);
        log.info("Inserting pod log into BigQuery table: {}.{}", k8sDataSet, podLogTable);


        Map<String, Object> rowContentMap = new HashMap<>();
        rowContentMap.put("uuid", UUID.randomUUID().toString());
        rowContentMap.put("podName", podName);
        rowContentMap.put("nameSpace", nameSpace);
        rowContentMap.put("podVersion", podVersion);
        rowContentMap.put("action", Action.DELETED.getValue());
        rowContentMap.put("actionTime", Instant.now().toString());

        InsertAllRequest.RowToInsert rowToInsert = InsertAllRequest.RowToInsert.of(rowContentMap);
        InsertAllRequest insertAllRequest = InsertAllRequest.newBuilder(k8sDataSet, podLogTable).addRow(rowToInsert).build();

        try {
            InsertAllResponse response = bigQuery.insertAll(insertAllRequest);
            if (response.hasErrors()) {
                log.error("Error in insertPodLog...{}", response.getInsertErrors());
                throw new Exception("Error in insertPodLog...");
            }
        } catch (Exception e) {
            log.error("Error in insertPodLog...", e);
            throw e;
        }
        log.info("Pod log inserted successfully...");
    }
}

