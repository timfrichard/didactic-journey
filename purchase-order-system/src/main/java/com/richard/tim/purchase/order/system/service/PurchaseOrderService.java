package com.richard.tim.purchase.order.system.service;

import com.google.common.collect.Lists;
import com.richard.tim.purchase.order.system.model.entities.PurchaseOrder;
import com.richard.tim.purchase.order.system.repository.PurchaseOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class PurchaseOrderService {

    @Value("${spring.jpa.properties.jdbc.batch_size:10}")
    private Integer batchSize;

    @PersistenceContext
    private EntityManager entityManager;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(final PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public List<PurchaseOrder> findAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder findById(final Long purchaseOrderId) {
        final PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(purchaseOrderId);
        return purchaseOrder;
    }

    public PurchaseOrder save(final PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Transactional
    public List<PurchaseOrder> saveAll(final List<PurchaseOrder> purchaseOrders) {
        List<PurchaseOrder> returnPurchaseOrders = Lists.newArrayList();
        purchaseOrders.forEach(purchaseOrder -> {
            log.info("Before saving purchase order {}", purchaseOrder.getPurchaserName());
            returnPurchaseOrders.add(purchaseOrderRepository.save(purchaseOrder));
            log.info("Done saving purchase order id: {}", purchaseOrder.getPurchaseOrderId());
        });
        return returnPurchaseOrders;
    }

    @Transactional
    public List<PurchaseOrder> saveAllWithFlush(final List<PurchaseOrder> purchaseOrders) {

        AtomicInteger i = new AtomicInteger();
        List<PurchaseOrder> returnPurchaseOrders = Lists.newArrayList();
        purchaseOrders.forEach(purchaseOrder -> {
            if (i.get() > 0 && i.get() % batchSize == 0) {
                log.info("FLUSHING");
                entityManager.flush();
                entityManager.clear();
            }
            log.info("Before saving purchase order {}", purchaseOrder.getPurchaserName());
            returnPurchaseOrders.add(purchaseOrderRepository.save(purchaseOrder));
            log.info("Done saving purchase order id: {}", purchaseOrder.getPurchaseOrderId());
            i.getAndIncrement();
        });
        return returnPurchaseOrders;
    }
}
