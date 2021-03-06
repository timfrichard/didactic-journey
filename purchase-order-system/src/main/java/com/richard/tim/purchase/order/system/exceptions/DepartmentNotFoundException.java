package com.richard.tim.purchase.order.system.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentNotFoundException extends RuntimeException {

    private String message;
}
