package com.home.api.service;

import com.home.api.dto.User;
import com.home.api.entity.ServiceInfoDao;

public interface UserService {
     User getUserById(Long id);
     ServiceInfoDao getServiceInfo();
}
