package com.example.estote.entity;

import com.example.estote.entityDetail.UserSex;
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
    private long id;

//    @Pattern(regexp = "\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}", message = "Некорректный формат номера телефона")
    @Column(name = "phone_user")
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
//    @NotEmpty(message = "Заполните e-mail")
    @Column(name = "e-mail_user")
    private String emailUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_birth")
    private Date dateBirth;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "sex_user")
    private UserSex sexUser;

    @Column(name = "role")
    private String role;

//    @Column (name = "archive_user")
//    private boolean isArchive;

    @Column(name = "recording_date")
    private LocalDateTime recordingDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

}

