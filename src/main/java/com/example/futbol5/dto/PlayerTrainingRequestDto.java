package com.example.futbol5.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerTrainingRequestDto {

    @NotBlank(message = "El nombre del jugador es requerido")
    private String playerName;

    @NotNull(message = "La potencia de tiro es requerida")
    @Min(value = 0, message = "La potencia de tiro debe ser mayor a 0")
    private Double shootingPower;

    @NotNull(message = "La velocidad es requerida")
    @Min(value = 0, message = "La velocidad debe ser mayor a 0")
    private Double speed;

    @NotNull(message = "Los pases efectivos son requeridos")
    @Min(value = 0, message = "Los pases no pueden ser negativos")
    private Integer effectivePasses;
}