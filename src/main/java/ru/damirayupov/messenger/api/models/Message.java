package ru.damirayupov.messenger.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User sender;
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    User recipient;
    String body;
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    Conversation conversation;
    @DateTimeFormat
    Date createdAt = Date.from(Instant.now());

}
