package com.home.api.controller;

import com.home.api.dto.User;
import com.home.api.entity.ApiResponse;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable("id") Long id) {
        User user = null;
        try {
            user = this.userService.getUserById(id);
            ApiResponse<User> response = new ApiResponse<>();
            response.setData(user);
            response.setReturnCode(0);
            response.setReturnMsg("User fetched successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getUserById...", e);
            ApiResponse<User> response = new ApiResponse<>();
            response.setReturnCode(-1);
            response.setReturnMsg("Error in fetching user: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
