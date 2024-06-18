package com.home.api.monitor;

import com.google.cloud.bigquery.BigQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class BigQueryHealthCheck {

    @Autowired
    private BigQuery bigQuery;

    public boolean isBigQueryHealthy() {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            try {
                bigQuery.listDatasets();
                return true;
            } catch (Exception e) {
                log.error("Error in BigQueryHealthCheck...", e);
                return false;
            }
        });

        try {
            // set time out to 5 seconds,  to wait for the return of future object.
            return future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("Timeout or error while checking BigQuery connection", e);
            return false;
        }
    }
}
