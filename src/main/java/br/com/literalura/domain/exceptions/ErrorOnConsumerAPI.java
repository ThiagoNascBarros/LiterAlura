package br.com.literalura.domain.exceptions;

public class ErrorOnConsumerAPI extends RuntimeException {
    public ErrorOnConsumerAPI(String message) {
        super(message);
    }
}
