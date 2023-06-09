package com.example.estote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "receipt_of_goods")
public class Receipt {

    @Id
    @Column (name = "id_receipt")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable (name = "good_receipt",
            joinColumns = @JoinColumn(name = "id_receipt"),
            inverseJoinColumns = @JoinColumn(name = "id_good"))
    private List <Good> goods;

    @Column (name = "amount_of_good")
    private int amountGoods;

    @Column (name = "date_receipt")
    private LocalDateTime date;

    private transient Map <Long, Integer> receipt = new HashMap<>();


}