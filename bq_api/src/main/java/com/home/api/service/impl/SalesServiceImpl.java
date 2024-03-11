package com.home.api.service.impl;

import com.home.api.dao.SalesDao;
import com.home.api.entity.SalesDetailsDao;
import com.home.api.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesDao salesDao;
    @Override
    public List<SalesDetailsDao> getSalesDetails() throws InterruptedException, Exception {
        return this.salesDao.getSalesDetails();
    }
}
