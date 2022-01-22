package com.richard.tim.purchase.order.system.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Column(name = "DEPARTMENT_ID", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence_gen")
    @SequenceGenerator(allocationSize = 10, name = "department_sequence_gen", sequenceName = "DEPT_ID_SEQ")
    private Long departmentId;

    @Column(name = "FLOOR")
    private Integer floor;

    @Column(name = "NAME")
    private String name;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private Set<PurchaseOrder> purchaseOrders;
}
