package com.example.user.exceptions;

import org.hibernate.TypeMismatchException;

public class MethodArgumentTypeMismatchException extends TypeMismatchException {

    public MethodArgumentTypeMismatchException(String message) {
        super(message);
    }

    public MethodArgumentTypeMismatchException() {
        super("Tipo de id inválido");
    }
}
