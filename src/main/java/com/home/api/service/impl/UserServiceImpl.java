package com.home.api.service.impl;

import com.home.api.clients.UserClient;
import com.home.api.dto.User;
import com.home.api.entity.ServiceInfoDao;
import com.home.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    @Override
    public User getUserById(Long id) {
        log.info("getUserById: id={}", id);
        return userClient.getUserById(id);
    }

    @Override
    public ServiceInfoDao getServiceInfo() {
        log.info("getServiceInfo()...");
        return userClient.getServiceInfo();
    }
}
