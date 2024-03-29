package com.home.api.dao.impl;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryError;
import com.home.api.enums.Action;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.google.cloud.bigquery.InsertAllResponse;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@Slf4j
class K8sDaoImplTest {



    @Mock
    BigQuery bigQuery;

    @InjectMocks
    private K8sDaoImpl k8sDaoImpl;

    @BeforeEach
    void setUp() {
        log.info("Setting up the test...");
        ReflectionTestUtils.setField(k8sDaoImpl, "podLogTable", "POD_LOG");
        ReflectionTestUtils.setField(k8sDaoImpl, "k8sDataSet", "DSK8S");
    }

    @Test
    void insertPodLog() {

        given(bigQuery.insertAll(any())).willReturn(null);
        assertDoesNotThrow(() -> k8sDaoImpl.insertPodLog("podName", "namespace", "podVersion"));
    }


    @Test
    void testAction() {

        Action action = Action.DELETED;
        log.info(action.getValue());
        assertEquals("DELETED".toLowerCase(), action.getValue());
    }


}