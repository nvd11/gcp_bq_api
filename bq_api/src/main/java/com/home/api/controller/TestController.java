package com.home.api.controller;

import com.home.api.entity.ApiResponse;
import com.home.api.entity.SalesDetailsDao;
import com.home.api.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello/fail")
    public ResponseEntity<ApiResponse<String>> getSalesDetails() {
        log.error("/test/hello/fail ... this api will already return 500 error");
        ApiResponse<String> response = new ApiResponse<>();
        response.setReturnCode(-1);
        response.setReturnMsg("this api will already return 500 error");
        return ResponseEntity.status(500).body(response);
    }

    @PostMapping("/hello/post")
    public ResponseEntity<ApiResponse<String>> postSalesDetails() {
        log.info("/test/hello/post ... this api will return 200 success");
        ApiResponse<String> response = new ApiResponse<>();
        response.setReturnCode(0);
        response.setReturnMsg("this api will return 200 success");
        return ResponseEntity.ok(response);
    }
}
