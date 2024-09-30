package com.sebn.pfe.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;

    private String lastName;

    private String description;
    private String etat = "pending";

    private Integer experience;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    // Optional relationship with Offre
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "offer_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Offre offre;

    // Optional relationship with Stage
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "stage_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Stage stage;

    // Optional relationship with Event
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "event_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Event event;
}
