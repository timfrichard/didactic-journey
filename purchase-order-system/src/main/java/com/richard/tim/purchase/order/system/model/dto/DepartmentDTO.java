package com.richard.tim.purchase.order.system.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class DepartmentDTO {

    private Long departmentId;

    @NotNull(message = "Department Name is required.")
    private String name;

    @NotNull(message = "Department floor is required.")
    private Integer floor;
}
