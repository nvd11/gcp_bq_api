package com.home.api.dao;

import com.home.api.entity.SalesDetailsDao;

import java.util.List;

public interface SalesDao {
    public List<SalesDetailsDao> getSalesDetails() throws InterruptedException, Exception;
}
