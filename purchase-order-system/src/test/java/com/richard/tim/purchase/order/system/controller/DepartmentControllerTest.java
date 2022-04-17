package com.richard.tim.purchase.order.system.controller;

import com.richard.tim.purchase.order.system.model.dto.DepartmentDTO;
import com.richard.tim.purchase.order.system.model.entities.Department;
import com.richard.tim.purchase.order.system.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    public void findDepartmentById(){

        final String myDepartment = "My Department";
        Optional<Department> department = Optional.of(Department.builder().departmentId(1L).floor(Integer.valueOf(20))
                .name(myDepartment).build());
        when(departmentService.findById(1L)).thenReturn(department);

        ResponseEntity<DepartmentDTO> foundDepartment = departmentController.findDepartmentById(1L);
        assertNotNull(foundDepartment.getBody());
        assertEquals(foundDepartment.getBody().getName(), myDepartment);
    }
}
