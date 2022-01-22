package com.richard.tim.purchase.order.system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ApiErrorDTO {

    private List<String> errors;
    private String message;
    private HttpStatus status;

}
