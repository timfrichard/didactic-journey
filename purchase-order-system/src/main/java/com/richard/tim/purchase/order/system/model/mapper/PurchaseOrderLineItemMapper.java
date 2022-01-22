package com.richard.tim.purchase.order.system.model.mapper;


import com.richard.tim.purchase.order.system.model.dto.PurchaseOrderLineItemDTO;
import com.richard.tim.purchase.order.system.model.entities.PurchaseOrderLineItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchaseOrderLineItemMapper {

    PurchaseOrderLineItemMapper MAPPER = Mappers.getMapper(PurchaseOrderLineItemMapper.class);

    PurchaseOrderLineItem dtoToPurchaseOrderLineItem(PurchaseOrderLineItemDTO purchaseOrderLineItemDTO);

    PurchaseOrderLineItemDTO purchaseOrderLineItemToDTO(PurchaseOrderLineItem purchaseOrderLineItem);
}
