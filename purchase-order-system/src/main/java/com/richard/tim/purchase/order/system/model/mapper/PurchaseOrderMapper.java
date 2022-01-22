package com.richard.tim.purchase.order.system.model.mapper;

import com.richard.tim.purchase.order.system.model.dto.PurchaseOrderDTO;
import com.richard.tim.purchase.order.system.model.entities.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PurchaseOrderLineItemMapper.class, DepartmentMapper.class})
public interface PurchaseOrderMapper {

    PurchaseOrderMapper MAPPER = Mappers.getMapper(PurchaseOrderMapper.class);

    PurchaseOrder dtoToPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    PurchaseOrderDTO purchaseOrderToDto(PurchaseOrder purchaseOrder);
}
