package com.example.futbol5.controller;

import com.example.futbol5.dto.MessageResponseDto;
import com.example.futbol5.dto.StarterTeamResponseDto;
import com.example.futbol5.dto.TrainingRequestDto;
import com.example.futbol5.service.TrainingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDto> registerTraining(
            @Valid @RequestBody TrainingRequestDto request) {
        MessageResponseDto response = trainingService.registerTraining(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/starter-team")
    public ResponseEntity<StarterTeamResponseDto> getStarterTeam() {
        StarterTeamResponseDto response = trainingService.getStarterTeam();
        return ResponseEntity.ok(response);
    }
}