package br.com.iser.liveloback.validation.exception;

public class ClientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String string) {
        super(string);
    }
}
