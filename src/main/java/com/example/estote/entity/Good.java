package com.example.estote.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "goods")
public class Good {

    @Id
    @Column (name = "id_good")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

//    @Size(min = 9, max = 9, message = "Article should contains 9 characters")
    @Column (name = "article_good")
    private long article;

    @NonNull
    @Column (name = "barcode_good")
    private long barcode;

    @Column (name = "name_good")
    private String name;

    @Column (name = "description_good")
    private String description;

    @Column (name = "prime_cost_good")
    private int primeCost;

    @Column (name = "cost_before_sales_good")
    private int costBeforeSale;

    @Column (name = "sale")
    private int sale;

    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable (name = "good_receipt",
            joinColumns = @JoinColumn(name = "id_good"),
            inverseJoinColumns = @JoinColumn(name = "id_receipt"))
    private List <Receipt> receipts;

    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable (
            name = "good_cart",
            joinColumns = @JoinColumn(name = "good_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    List <Cart> carts;

    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable (
            name = "order_good",
            joinColumns = @JoinColumn(name = "good_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    List <Order> orders;

//    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable (name = "good_write_off",
//            joinColumns = @JoinColumn(name = "id_good"),
//            inverseJoinColumns = @JoinColumn(name = "id_write_off"))
//    private List <WriteOff> writesOff;


}
