package com.richard.tim.purchase.order.system.service;

import com.richard.tim.purchase.order.system.model.entities.PurchaseOrder;
import com.richard.tim.purchase.order.system.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(final PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public List<PurchaseOrder> findAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder findById(final Long purchaseOrderId) {
        final PurchaseOrder purchaseOrder = purchaseOrderRepository.getById(purchaseOrderId);
        return purchaseOrder;
    }

    public PurchaseOrder save(final PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
