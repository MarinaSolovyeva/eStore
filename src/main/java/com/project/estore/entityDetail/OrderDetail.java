package com.project.estore.entityDetail;

import com.project.estore.entity.Good;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderDetail {

    private String name;
    private Long goodId;
    private Integer price;
    private BigDecimal amount;
    private Double sum;

    public OrderDetail(Good good) {
        this.name = good.getName();
        this.goodId = good.getId();
        this.price = good.getCostBeforeSale() * good.getSale()/100;
        this.amount = new BigDecimal(1.0);
        Integer priceI = price;
        this.sum = Double.valueOf(priceI.toString());
    }

}
