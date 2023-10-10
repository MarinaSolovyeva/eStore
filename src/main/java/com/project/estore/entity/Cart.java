package com.project.estore.entity;

import com.project.estore.entityDetail.CartDetail;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table (name = "carts")
public class Cart {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Long idCart;

    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable (name = "good_cart",
            joinColumns = @JoinColumn (name = "cart_id"),
            inverseJoinColumns = @JoinColumn (name = "good_id"))
    private List<Good> goods;

    private transient int amountGoods;
    private transient Double sum;

    private transient List <CartDetail> cartDetails = new ArrayList<>();

    public void aggregate() {
        this.amountGoods = cartDetails.size();
        this.sum = cartDetails.stream()
                .map(CartDetail::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();}

}