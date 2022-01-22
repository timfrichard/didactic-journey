package com.richard.tim.purchase.order.system.repository;

import com.richard.tim.purchase.order.system.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
