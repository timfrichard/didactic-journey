package com.richard.tim.purchase.order.system.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "PURCHASE_ORDER")
@ToString
public class PurchaseOrder {

    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_DEPARTMENT_ID", nullable = false)
    private Department department;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PURCHASE_ORDER_ID", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "purchase_order_sequence_gen")
    @SequenceGenerator(name = "purchase_order_sequence_gen", sequenceName = "PURCHASE_ORDER_ID_SEQ", schema = "PURCHASE_ORDER_SYSTEM_SCHEMA", allocationSize = 1)
    private Long purchaseOrderId;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrder", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<PurchaseOrderLineItem> purchaseOrderLineItems;

    @Column(name = "PURCHASER_NAME")
    private String purchaserName;

    @Column(name = "SUBMIT_DATE")
    private LocalDate submitDate;
}
