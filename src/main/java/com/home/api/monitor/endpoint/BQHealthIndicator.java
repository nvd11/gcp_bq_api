package com.home.api.monitor.endpoint;

import com.home.api.monitor.BigQueryHealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class BQHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    private BigQueryHealthCheck bigQueryHealthCheck;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (bigQueryHealthCheck.isBigQueryHealthy()) {
            //int i=1/0;
            builder.up();
        } else {
            builder.down();
        }
    }

}
