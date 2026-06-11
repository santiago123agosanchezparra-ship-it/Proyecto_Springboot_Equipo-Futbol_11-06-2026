package com.example.futbol5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarterTeamResponseDto {

    private String message;
    private List<PlayerResultResponseDto> starters;
}