package me.dio.gym.services.exceptions;

public class CepNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CepNotFoundException(Object cep) {
        super("Cep not found: " + cep);
    }
}
