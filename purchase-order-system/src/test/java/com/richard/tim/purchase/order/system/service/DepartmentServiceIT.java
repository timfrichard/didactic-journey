package com.richard.tim.purchase.order.system.service;

import com.richard.tim.purchase.order.system.model.entities.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepartmentServiceIT {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void findByIdTest(){
        Optional<Department> department = departmentService.findById(1L);
        assertNotNull(department.get());
        assertEquals(department.get().getName(), "Human Resource");
    }
}
