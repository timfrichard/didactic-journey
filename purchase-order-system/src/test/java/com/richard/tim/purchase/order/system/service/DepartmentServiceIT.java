package com.richard.tim.purchase.order.system.service;

import com.richard.tim.purchase.order.system.model.entities.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
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
