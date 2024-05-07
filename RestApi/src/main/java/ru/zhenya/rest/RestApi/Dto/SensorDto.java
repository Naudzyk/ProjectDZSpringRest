package ru.zhenya.rest.RestApi.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDto {
    @NotEmpty(message = "Пустоe значение name")
    @Size(min = 3,max = 30,message = "Название сенсора должно быть от 3 до 30")
    private String name;


}
