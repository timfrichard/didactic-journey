package com.richard.tim.purchase.order.system.service;

import com.richard.tim.purchase.order.system.model.entities.Department;
import com.richard.tim.purchase.order.system.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(final Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    public Department save(final Department deparment) {
        return departmentRepository.save(deparment);
    }
}
