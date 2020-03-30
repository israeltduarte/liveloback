package br.com.iser.liveloback.validation.exception;

public class CityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CityNotFoundException(String string) {
        super(string);
    }
}
