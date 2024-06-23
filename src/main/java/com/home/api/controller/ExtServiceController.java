package com.home.api.controller;

import com.home.api.dto.User;
import com.home.api.entity.ApiResponse;
import com.home.api.entity.ServiceInfoDao;
import com.home.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/ext-service")
public class ExtServiceController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-service/info")
    public ResponseEntity<ApiResponse<ServiceInfoDao>> userServiceInfo() {
        ServiceInfoDao userServiceInfo = null;
        try {
            userServiceInfo = this.userService.getServiceInfo();
            ApiResponse<ServiceInfoDao> response = new ApiResponse<>();
            response.setData(userServiceInfo);
            response.setReturnCode(0);
            response.setReturnMsg("user service is running in the host: " + userServiceInfo.getHostname());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getUserById...", e);
            ApiResponse<ServiceInfoDao> response = new ApiResponse<>();
            response.setReturnCode(-1);
            response.setReturnMsg("Error in getting user service info: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
