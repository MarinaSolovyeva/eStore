package com.example.estote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "addresses")
public class Address {

    @Id
    @Column (name = "id_address")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column (name = "address_address")
    private String addressName;

    @Column (name = "comment_address")
    private String addressComment;
}
