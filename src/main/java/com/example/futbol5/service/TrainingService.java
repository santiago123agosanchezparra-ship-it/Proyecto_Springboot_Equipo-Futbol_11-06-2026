package com.example.futbol5.service;

import com.example.futbol5.dto.MessageResponseDto;
import com.example.futbol5.dto.PlayerResultResponseDto;
import com.example.futbol5.dto.PlayerTrainingRequestDto;
import com.example.futbol5.dto.StarterTeamResponseDto;
import com.example.futbol5.dto.TrainingRequestDto;
import com.example.futbol5.entity.PlayerTraining;
import com.example.futbol5.exception.TrainingAlreadyRegisteredException;
import com.example.futbol5.repository.PlayerTrainingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {

    private final PlayerTrainingRepository playerTrainingRepository;

    public TrainingService(PlayerTrainingRepository playerTrainingRepository) {
        this.playerTrainingRepository = playerTrainingRepository;
    }

    public MessageResponseDto registerTraining(TrainingRequestDto request) {
        if (playerTrainingRepository.existsByTrainingNumber(request.getTrainingNumber()) == true) {
            throw new TrainingAlreadyRegisteredException(request.getTrainingNumber());
        }

        List<PlayerTraining> playerTrainings = new ArrayList<>();
        
        for (int i = 0; i < request.getPlayers().size(); i++) {
            PlayerTrainingRequestDto dto = request.getPlayers().get(i);
            
            double score = (dto.getShootingPower() * 0.20)
                    + (dto.getSpeed() * 0.30)
                    + (dto.getEffectivePasses() * 0.50);

            PlayerTraining player = new PlayerTraining();
            player.setPlayerName(dto.getPlayerName());
            player.setTrainingNumber(request.getTrainingNumber());
            player.setShootingPower(dto.getShootingPower());
            player.setSpeed(dto.getSpeed());
            player.setEffectivePasses(dto.getEffectivePasses());
            player.setScore(score);
            
            playerTrainings.add(player);
        }

        playerTrainingRepository.saveAll(playerTrainings);

        return new MessageResponseDto(
                "Entrenamiento #" + request.getTrainingNumber() + " registrado exitosamente."
        );
    }

    public StarterTeamResponseDto getStarterTeam() {
        
        boolean faltanEntrenamientos = false;
        for (int i = 1; i <= 3; i++) {
            if (playerTrainingRepository.existsByTrainingNumber(i) == false) {
                faltanEntrenamientos = true;
                break;
            }
        }

        if (faltanEntrenamientos == true) {
            return new StarterTeamResponseDto(
                    "No hay suficiente informacion. Se requieren los 3 entrenamientos de la semana.",
                    null
            );
        }

        List<PlayerTraining> allTrainings = playerTrainingRepository.findAll();

        List<String> nombresUnicos = new ArrayList<>();
        
        for (int i = 0; i < allTrainings.size(); i++) {
            String name = allTrainings.get(i).getPlayerName();
            
            boolean yaExiste = false;
            for (int j = 0; j < nombresUnicos.size(); j++) {
                if (nombresUnicos.get(j).equals(name)) {
                    yaExiste = true;
                    break;
                }
            }
            
            if (yaExiste == false) {
                nombresUnicos.add(name);
            }
        }

        List<PlayerResultResponseDto> allResults = new ArrayList<>();
        
        for (int i = 0; i < nombresUnicos.size(); i++) {
            String name = nombresUnicos.get(i);
            double sumaPuntajes = 0.0;
            int contador = 0;

            for (int j = 0; j < allTrainings.size(); j++) {
                if (allTrainings.get(j).getPlayerName().equals(name)) {
                    sumaPuntajes = sumaPuntajes + allTrainings.get(j).getScore();
                    contador = contador + 1;
                }
            }

            double promedio = sumaPuntajes / contador;
            
            double promedioRedondeado = ((int) (promedio * 100)) / 100.0;

            allResults.add(new PlayerResultResponseDto(name, promedioRedondeado));
        }

        for (int i = 0; i < allResults.size() - 1; i++) {
            for (int j = 0; j < allResults.size() - i - 1; j++) {
                if (allResults.get(j).getScore() < allResults.get(j + 1).getScore()) {
                    
                    PlayerResultResponseDto temporal = allResults.get(j);
                    allResults.set(j, allResults.get(j + 1));
                    allResults.set(j + 1, temporal);
                }
            }
        }

        List<PlayerResultResponseDto> starters = new ArrayList<>();
        for (int i = 0; i < allResults.size(); i++) {
            if (i < 5) {
                starters.add(allResults.get(i));
            } else {
                break;
            }
        }

        return new StarterTeamResponseDto("Equipo titular generado exitosamente.", starters);
    }
}