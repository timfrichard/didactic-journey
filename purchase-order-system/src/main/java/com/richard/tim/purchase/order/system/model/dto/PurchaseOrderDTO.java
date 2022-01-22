package com.richard.tim.purchase.order.system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class PurchaseOrderDTO {

    @NotNull(message = "Department is required for a purchase order.")
    private DepartmentDTO department;

    @NotNull(message = "A description is required for a purchase order.")
    private String description;

    private Long purchaseOrderId;

    @NotNull(message = "Purchaser name is required for a purchase order.")
    private String purchaserName;

    private List<PurchaseOrderLineItemDTO> purchaseOrderLineItems;

    @NotNull(message = "Submit date is required for a purchase order.")
    private LocalDate submitDate;

}
