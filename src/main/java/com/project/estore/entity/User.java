package com.project.estore.entity;

import com.project.estore.entityDetail.GenderUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_user")
    private String password;

    @OneToMany (mappedBy = "user",
    cascade = CascadeType.ALL)
    private List <Address> addresses;

    @NotEmpty(message = "Заполните имя")
    @Column(name = "name_surname_user")
    private String nameSurname;

    @Email(message = "E-mail адрес введен неправильно")
    @Column(name = "e-mail_user")
    private String emailUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_birth")
    private Date dateBirth;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "gender_user")
    private GenderUser genderUser;

    @Column(name = "role")
    private String role;

    @Column (name = "archive_user")
    private Boolean isArchive;

    @Column(name = "recording_date")
    private LocalDateTime recordingDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

}

