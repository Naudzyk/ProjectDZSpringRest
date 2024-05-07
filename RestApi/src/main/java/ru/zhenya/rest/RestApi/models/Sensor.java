package ru.zhenya.rest.RestApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "sensor")
public class Sensor implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Пусто")
    @Size(min = 3,max=30, message = "Название хуевое")
    @Column(name = "name")
    private String name;

}
