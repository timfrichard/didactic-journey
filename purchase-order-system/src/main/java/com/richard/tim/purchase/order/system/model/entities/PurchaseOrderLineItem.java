package com.richard.tim.purchase.order.system.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "PURCHASE_ORDER_LINE_ITEM")
public class PurchaseOrderLineItem {

    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_PURCHASE_ORDER_ID", nullable = false)
    private PurchaseOrder purchaseOrder;

    @Column(name = "PO_LINE_ITEM_ID", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "po_line_item_sequence_gen")
    @SequenceGenerator(name = "po_line_item_sequence_gen", sequenceName = "PO_LINE_ITEM_ID_SEQ", schema = "PURCHASE_ORDER_SYSTEM_SCHEMA", allocationSize = 1)
    private Long purchaseOrderLineItemId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL_PRICE", precision = 20, scale = 2)
    private BigDecimal total;

    @Column(name = "UNIT_PRICE", precision = 20, scale = 2)
    private BigDecimal unitPrice;
}
