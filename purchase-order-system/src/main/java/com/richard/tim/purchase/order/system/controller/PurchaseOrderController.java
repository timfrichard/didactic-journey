package com.richard.tim.purchase.order.system.controller;

import com.google.common.collect.Lists;
import com.richard.tim.purchase.order.system.model.dto.PurchaseOrderDTO;
import com.richard.tim.purchase.order.system.model.entities.PurchaseOrder;
import com.richard.tim.purchase.order.system.model.mapper.PurchaseOrderMapper;
import com.richard.tim.purchase.order.system.service.PurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("managePurchaseOrder")
public class PurchaseOrderController {

    public static final String NEW_PO_MSG = "This should be a new PurchaseOrder.";
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(final PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("{purchaseOrderId}")
    public PurchaseOrderDTO findPurchaseOrderById(@PathVariable(name = "purchaseOrderId")
                                                  @NotNull final Long purchaseOrderId) {

        return PurchaseOrderMapper.MAPPER.purchaseOrderToDto(purchaseOrderService.findById(purchaseOrderId));
    }

    @PostMapping()
    public PurchaseOrderDTO savePurchaseOrder(@Valid @RequestBody final PurchaseOrderDTO purchaseOrderDTO) {

        Validate.isTrue(purchaseOrderDTO.getPurchaseOrderId() == null, NEW_PO_MSG, "Test");

        final PurchaseOrder purchaseOrder = buildPurchaseOrder(purchaseOrderDTO);

        return PurchaseOrderMapper.MAPPER.purchaseOrderToDto(
                purchaseOrderService.save(purchaseOrder));
    }

    @PostMapping("/saveAll")
    public List<PurchaseOrderDTO> savePurchaseOrders(@Valid @RequestBody final List<PurchaseOrderDTO> purchaseOrderDTOS) {

        List<PurchaseOrderDTO> returnDTOs = Lists.newArrayList();
        List<PurchaseOrder> purchaseOrders = Lists.newArrayList();
        purchaseOrderDTOS.forEach(purchaseOrderDTO -> purchaseOrders.add(buildPurchaseOrder(purchaseOrderDTO)));

        purchaseOrderService.saveAll(purchaseOrders).forEach(purchaseOrder ->
                returnDTOs.add(PurchaseOrderMapper.MAPPER.purchaseOrderToDto(purchaseOrder)));

        return returnDTOs;
    }

    @PostMapping("/saveAllWithFlush")
    public List<PurchaseOrderDTO> savePurchaseOrdersWithFlush(@Valid @RequestBody final List<PurchaseOrderDTO> purchaseOrderDTOS) {

        List<PurchaseOrderDTO> returnDTOs = Lists.newArrayList();
        List<PurchaseOrder> purchaseOrders = Lists.newArrayList();
        purchaseOrderDTOS.forEach(purchaseOrderDTO -> purchaseOrders.add(buildPurchaseOrder(purchaseOrderDTO)));

        purchaseOrderService.saveAllWithFlush(purchaseOrders).forEach(purchaseOrder ->
                returnDTOs.add(PurchaseOrderMapper.MAPPER.purchaseOrderToDto(purchaseOrder)));

        return returnDTOs;
    }

    @PutMapping()
    public PurchaseOrderDTO updatePurchaseOrder(@Valid @RequestBody final PurchaseOrderDTO purchaseOrderDTO) {

        Validate.notNull(purchaseOrderDTO.getPurchaseOrderId());

        final PurchaseOrder purchaseOrder = buildPurchaseOrder(purchaseOrderDTO);
        return PurchaseOrderMapper.MAPPER.purchaseOrderToDto(
                purchaseOrderService.save(purchaseOrder));
    }

    private PurchaseOrder buildPurchaseOrder(final PurchaseOrderDTO purchaseOrderDTO) {

        /* There is work needed on the PurchaseOrder object for Line Items */
        final PurchaseOrder purchaseOrder = PurchaseOrderMapper.MAPPER.dtoToPurchaseOrder(purchaseOrderDTO);
        /* Add the PurchaseOrder to the PurchaseOrderLineItem */
        purchaseOrder.getPurchaseOrderLineItems().forEach(purchaseOrderLineItem -> {
            purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
        });

        return purchaseOrder;
    }
}
