package com.home.api.dao.impl;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.home.api.dao.SalesDao;
import com.home.api.entity.SalesDetailsDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.google.cloud.bigquery.BigQueryOptions;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SalesDaoImpl implements SalesDao {

    //@Autowired
    private BigQuery bigquery;

    @Value("${FullSales}")
    private String sqlSales;

    @Override
    public List<SalesDetailsDao> getSalesDetails() throws Exception {


        bigquery = BigQueryOptions.getDefaultInstance().getService();




        List<SalesDetailsDao> list = new ArrayList<>();
        try {
            log.info("BigQuery instance created..");
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(sqlSales).build();

            for (FieldValueList row: bigquery.query(queryConfig).iterateAll()) {
                SalesDetailsDao salesDetails = SalesDetailsDao.builder()
                    .orderId(row.get(0).getStringValue())
                    .product(row.get(1).getStringValue())
                    .QuantityOrdered(row.get(2).getLongValue())
                    .Price_Each(row.get(3).getNumericValue().floatValue())
                    .OrderDateStr(row.get(4).getStringValue())
                    .PurchaseAddr(row.get(5).getStringValue())
                    .build();
                list.add(salesDetails);
            }

        }catch (Exception e){
            log.error("Error in getSalesDetails...", e);
            throw e;
        }

        return list;
    }
}
