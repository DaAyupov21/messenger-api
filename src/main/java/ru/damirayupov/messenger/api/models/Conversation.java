package ru.damirayupov.messenger.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    User recipient;
    @OneToMany(mappedBy = "conversation")
     List<Message> messages;
}
