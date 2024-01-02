package dev.lebenkov.authapi.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "account")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @Column(name = "username")
    String username;

    @Column(name = "role")
    String role;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "is_verified_email")
    Boolean isVerifiedEmail;

    @Column(name = "profile_picture")
    String profilePicture;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "first_name")
    String firstName;
}
