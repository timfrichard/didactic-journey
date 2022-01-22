package com.richard.tim.purchase.order.system.repository;

import com.richard.tim.purchase.order.system.model.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Serializable> {
}
