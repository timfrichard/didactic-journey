package com.richard.tim.purchase.order.system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class PurchaseOrderLineItemDTO {

    @NotNull(message = "Line item description is required for a purchase order line item.")
    private String itemDescription;

    private Long purchaseOrderLineItemId;

    @NotNull(message = "Quantity is required for a purchase order line item.")
    private Integer quantity;

    @NotNull(message = "Total is required for a purchase order line item.")
    private BigDecimal total;

    @NotNull(message = "Unit price is required for a purchase order line item.")
    private BigDecimal unitPrice;
}
