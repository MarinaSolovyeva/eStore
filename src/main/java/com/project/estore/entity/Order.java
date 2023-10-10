package com.project.estore.entity;

import com.project.estore.entityDetail.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "phone_order")
    private String phoneOrder;

    @Column(name = "comment_order")
    private String commentForOrder;

    @Column(name = "sum_order")
    private Double sumOrder;

    @Column(name = "datetime_of_creation")
    private LocalDateTime dateOfCreation;

    @Column(name = "datetime_of_delivery_order")
    private Date dateForDelivery;

//    @OneToOne
//    @JoinColumn(name = "status_id")
//    private StatusForOrder statusId;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "order_good",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "good_id"))
    private List<Good> goods;

    private transient List<Long> goodIDs;

    private transient int amountGoods;
    private transient Double sum;
    private transient List<OrderDetail> orderDetails = new ArrayList<>();

    public void aggregate() {
        this.amountGoods = orderDetails.size();
        this.sum = orderDetails.stream()
                .map(OrderDetail::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }


}



