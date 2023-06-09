package com.example.estote.entityDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class ReceiptFormModel {
    /**
     * The class is necessary to serialize all goods of receipt to POJO
     */
    private String allGoods;
    private Integer quantity;


}
