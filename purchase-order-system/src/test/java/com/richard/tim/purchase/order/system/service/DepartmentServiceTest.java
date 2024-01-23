package com.richard.tim.purchase.order.system.service;

import com.richard.tim.purchase.order.system.model.entities.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentService departmentService;

    @Test
    public void findByIdTest() {
        Optional<Department> department = Optional.of(Department.builder().departmentId(1L).floor(20)
                .name("My Department").build());
        when(departmentService.findById(1L)).thenReturn(department);

        Optional<Department> foundDepartment = departmentService.findById(1L);
        atLeastOnce();
        assertEquals(foundDepartment.get().getName(), "My Department");
    }
}
