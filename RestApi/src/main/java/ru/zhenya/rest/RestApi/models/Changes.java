package ru.zhenya.rest.RestApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "changes")
@Data
public class Changes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(-100)
    @NotNull
    @Max(100)
    @Column(name = "value")
    private Double value;

    @NotNull
    @Column(name = "raining")
    private Boolean raining;

    @NotNull
    @Column(name = "date_time")
    private LocalDateTime data_time;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor",referencedColumnName = "name")
    private Sensor sensor;

}
