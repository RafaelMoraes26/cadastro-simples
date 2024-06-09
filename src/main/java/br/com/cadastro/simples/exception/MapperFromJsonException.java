package br.com.cadastro.simples.exception;

public class MapperFromJsonException extends RuntimeException {

    public MapperFromJsonException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
