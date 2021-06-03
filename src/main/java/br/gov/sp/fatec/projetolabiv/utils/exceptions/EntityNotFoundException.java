package br.gov.sp.fatec.projetolabiv.utils.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private final Integer id;

    EntityNotFoundException(String message, Integer id) {
        super(message);
        this.id = id;
    }
}
