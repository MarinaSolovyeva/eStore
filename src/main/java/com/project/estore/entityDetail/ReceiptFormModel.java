package com.project.estore.entityDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class ReceiptFormModel {

    private String allGoods;
    private Integer quantity;


}
