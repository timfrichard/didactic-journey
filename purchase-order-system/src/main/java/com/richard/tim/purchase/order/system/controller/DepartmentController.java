package com.richard.tim.purchase.order.system.controller;

import com.google.common.collect.Lists;
import com.richard.tim.purchase.order.system.exceptions.DepartmentNotFoundException;
import com.richard.tim.purchase.order.system.model.dto.DepartmentDTO;
import com.richard.tim.purchase.order.system.model.mapper.DepartmentMapper;
import com.richard.tim.purchase.order.system.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("manageDepartment")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentDTO> findAllDepartments() {
        final List<DepartmentDTO> departmentDTOS = Lists.newArrayList();

        departmentService.findAllDepartments().forEach(department ->
                departmentDTOS.add(DepartmentMapper.MAPPER.departmentToDTO(department)));

        return departmentDTOS;
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<DepartmentDTO> findDepartmentById(
            @PathVariable(name = "departmentId") @NotNull final Long departmentId) {

        final DepartmentDTO departmentDTO = DepartmentMapper.MAPPER.departmentToDTO(
                departmentService.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(
                        "There is no Department with the given Id.")));

        return ResponseEntity.ok().body(departmentDTO);
    }
}
