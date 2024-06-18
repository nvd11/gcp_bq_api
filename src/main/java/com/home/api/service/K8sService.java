package com.home.api.service;

public interface K8sService {

    public void preStop(String podName, String nameSpace, String podVersion) throws Exception;
}
