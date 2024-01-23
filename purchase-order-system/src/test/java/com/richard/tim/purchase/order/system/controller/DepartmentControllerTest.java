package com.richard.tim.purchase.order.system.controller;

import com.richard.tim.purchase.order.system.model.dto.DepartmentDTO;
import com.richard.tim.purchase.order.system.model.entities.Department;
import com.richard.tim.purchase.order.system.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    public void findDepartmentById(){

        final String myDepartment = "My Department";
        Optional<Department> department = Optional.of(Department.builder().departmentId(1L).floor(20)
                .name(myDepartment).build());
        when(departmentService.findById(1L)).thenReturn(department);

        ResponseEntity<DepartmentDTO> foundDepartment = departmentController.findDepartmentById(1L);
        assertNotNull(foundDepartment.getBody());
        assertEquals(foundDepartment.getBody().getName(), myDepartment);
    }
}
