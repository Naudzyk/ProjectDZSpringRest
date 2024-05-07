package com.zhenya.org.Spring.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChangesDto {
    @NotEmpty(message = "Пустое значение value")
    private Double value;

    @NotEmpty(message = "Пустое значение raining")
    private Boolean raining;

    @NotEmpty(message = "Пустое значение data_time")
    private LocalDateTime data_time;
}
