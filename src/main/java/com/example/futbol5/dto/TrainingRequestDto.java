package com.example.futbol5.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainingRequestDto {

    @NotNull(message = "El numero de entrenamiento es requerido")
    @Min(value = 1, message = "El numero de entrenamiento debe estar entre 1 y 3")
    @Max(value = 3, message = "El numero de entrenamiento debe estar entre 1 y 3")
    private Integer trainingNumber;

    @NotEmpty(message = "El entrenamiento debe incluir al menos un jugador")
    @Valid
    private List<PlayerTrainingRequestDto> players;
}