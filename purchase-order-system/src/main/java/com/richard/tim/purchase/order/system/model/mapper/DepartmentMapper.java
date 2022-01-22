package com.richard.tim.purchase.order.system.model.mapper;


import com.richard.tim.purchase.order.system.model.dto.DepartmentDTO;
import com.richard.tim.purchase.order.system.model.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);

    Department dtoToDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO departmentToDTO(Department department);
}
