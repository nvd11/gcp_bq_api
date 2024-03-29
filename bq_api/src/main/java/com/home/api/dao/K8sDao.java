package com.home.api.dao;

public interface K8sDao {
    public void insertPodLog(String podName, String nameSpace, String podVersion) throws Exception;
}
