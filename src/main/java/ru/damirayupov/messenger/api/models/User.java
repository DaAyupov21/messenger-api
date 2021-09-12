package ru.damirayupov.messenger.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
//@EntityListeners(UserListener::class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    @Size(min = 2)
    String username;
    @Size(min = 11)
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    String phoneNumber;
    String password;
    String status;
    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    String accountStatus;
    @DateTimeFormat
    LocalDateTime createAt;
    @OneToMany(mappedBy = "sender")
    List<Message> sentMessages;
    @OneToMany(mappedBy = "recipient")
    List<Message> receivedMessages;
}
