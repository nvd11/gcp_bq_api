package com.home.api.service;

import com.home.api.entity.SalesDetailsDao;

import java.util.List;

public interface SalesService {
    public List<SalesDetailsDao> getSalesDetails() throws InterruptedException, Exception;
}
