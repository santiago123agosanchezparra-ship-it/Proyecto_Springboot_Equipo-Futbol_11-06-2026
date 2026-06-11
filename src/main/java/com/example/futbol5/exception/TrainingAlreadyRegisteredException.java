package com.example.futbol5.exception;

public class TrainingAlreadyRegisteredException extends RuntimeException {

    public TrainingAlreadyRegisteredException(Integer trainingNumber) {
        super("El entrenamiento #" + trainingNumber + " ya fue registrado.");
    }
}