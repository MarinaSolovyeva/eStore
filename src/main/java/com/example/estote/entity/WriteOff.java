package com.example.estote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "receipt_of_goods")
public class WriteOff {

    @Id
    @Column (name = "id_receipt")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idReceipt;

//    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable (name = "good_write_off",
//            joinColumns = @JoinColumn(name = "id_write_off"),
//            inverseJoinColumns = @JoinColumn(name = "id_good"))
//    private List<Good> goods;

    @Column (name = "amount_receipt")
    private int amountReceipt;

    @Column (name = "date_receipt")
    private Date date;
}

