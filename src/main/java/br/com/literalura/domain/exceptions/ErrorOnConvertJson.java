package br.com.literalura.domain.exceptions;

public class ErrorOnConvertJson extends RuntimeException {
    public ErrorOnConvertJson(String message) {
        super(message);
    }
}
