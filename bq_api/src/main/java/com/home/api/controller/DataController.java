package com.home.api.controller;

import com.home.api.entity.ApiResponse;
import com.home.api.entity.SalesDetailsDao;
import com.home.api.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/v1/data/sales")
    public ResponseEntity<ApiResponse<List<SalesDetailsDao>>> getSalesDetails() {
        ApiResponse<List<SalesDetailsDao>> response = new ApiResponse<>();
        try {
            List<SalesDetailsDao> list = salesService.getSalesDetails();
            response.setData(list);
            response.setReturnCode(0);
            response.setReturnMsg("Sales details fetched successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getSalesDetails...", e);
            response.setReturnCode(-1);
            response.setReturnMsg("Error in fetching sales details");
            return ResponseEntity.status(500).body(response);
        }

    }


}
