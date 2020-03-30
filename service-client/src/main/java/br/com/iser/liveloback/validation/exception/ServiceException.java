package br.com.iser.liveloback.validation.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceException(String string) {
        super(string);
    }
}
