package com.home.api.service.impl;

import com.home.api.dao.K8sDao;
import com.home.api.service.K8sService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class K8sServiceImpl implements K8sService {

        @Autowired
        private K8sDao k8sDao;

        @Override
        public void preStop(String podName, String nameSpace, String podVersion) throws Exception {
            log.info("PodName: " + podName + " Namespace: " + nameSpace + " PodVersion: " + podVersion);
            this.k8sDao.insertPodLog(podName, nameSpace, podVersion);

        }
}
