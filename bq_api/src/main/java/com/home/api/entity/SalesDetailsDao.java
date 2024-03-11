package com.home.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesDetailsDao {
    private String orderId;
    private String product;
    private Long QuantityOrdered;
    private Float Price_Each;
    private String OrderDateStr;
    private String PurchaseAddr;
}
