package com.home.api.controller;

import com.home.api.entity.ApiResponse;
import com.home.api.service.K8sService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class K8sController {

    @Autowired
    private K8sService k8sService;

    /**
     *
     * curl --location '127.0.0.1:8080/xxx/v1/k8s/prestop' \
     * --form 'podName="mypod"' \
     * --form 'nameSpace="12"' \
     * --form 'podVersion="11"'
     * @param podName
     * @param nameSpace
     * @param podVersion
     */
    @PostMapping("/v1/k8s/prestop")
    public ResponseEntity<ApiResponse<String>> preStop(@RequestParam(required = true) String podName,
                                               @RequestParam(required = true) String nameSpace,
                                               @RequestParam(required = true) String podVersion
                                                                            ) throws Exception {

        log.info("PodName: " + podName + " Namespace: " + nameSpace + " PodVersion: " + podVersion);
        try {
            k8sService.preStop(podName, nameSpace, podVersion);
            ApiResponse<String> response = new ApiResponse<>();
            response.setReturnCode(0);
            response.setReturnMsg("Pod log inserted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in preStop...", e);
            ApiResponse<String> response = new ApiResponse<>();
            response.setReturnCode(-1);
            response.setReturnMsg("Error in inserting pod log");
            return ResponseEntity.status(500).body(response);
        }
    }
}
