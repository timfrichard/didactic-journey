package com.richard.tim.purchase.order.system.controller;

import com.richard.tim.purchase.order.system.AbstractPurchaseOrderSystemIT;
import com.richard.tim.purchase.order.system.model.dto.DepartmentDTO;
import com.richard.tim.purchase.order.system.model.entities.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DepartmentControllerIT extends AbstractPurchaseOrderSystemIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testFindAllDepartments() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<DepartmentDTO>> response = restTemplate.exchange(
                "/manageDepartment",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<DepartmentDTO>>() {
                });

        assertNotNull(response);
        assertTrue(response.getBody().size() > 0);

    }

}
