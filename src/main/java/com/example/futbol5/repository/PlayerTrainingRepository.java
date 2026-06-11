package com.example.futbol5.repository;

import com.example.futbol5.entity.PlayerTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerTrainingRepository extends JpaRepository<PlayerTraining, Long> {

    List<PlayerTraining> findByTrainingNumber(Integer trainingNumber);

    boolean existsByTrainingNumber(Integer trainingNumber);
}