package com.example.futbol5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "player_trainings")
@Data
public class PlayerTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "training_number", nullable = false)
    private Integer trainingNumber;

    @Column(name = "shooting_power", nullable = false)
    private Double shootingPower;

    @Column(name = "speed", nullable = false)
    private Double speed;

    @Column(name = "effective_passes", nullable = false)
    private Integer effectivePasses;

    @Column(name = "score", nullable = false)
    private Double score;
}