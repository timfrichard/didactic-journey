package com.richard.tim.purchase.order.system.service;

import com.richard.tim.purchase.order.system.model.entities.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentService departmentService;

    @Test
    public void findByIdTest() {
        Optional<Department> department = Optional.of(Department.builder().departmentId(1L).floor(Integer.valueOf(20))
                .name("My Department").build());
        when(departmentService.findById(1L)).thenReturn(department);

        Optional<Department> foundDepartment = departmentService.findById(1L);
        atLeastOnce();
        assertEquals(foundDepartment.get().getName(), "My Department");
    }
}
