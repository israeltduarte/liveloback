package br.com.iser.liveloback.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.iser.liveloback.validation.exception.CityNotFoundException;
import br.com.iser.liveloback.validation.exception.ClientNotFoundException;
import br.com.iser.liveloback.validation.exception.SearchException;
import br.com.iser.liveloback.validation.exception.ServiceException;
import br.com.iser.liveloback.validation.exception.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String ISRAEL_DUARTE = "ISRAEL DUARTE";

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> customClientNotFound(Exception e) {

		CustomErrorResponse errors = new CustomErrorResponse();

		errors.setTimestamp(LocalDateTime.now());
		errors.setError(e.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setOwner(ISRAEL_DUARTE);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<CustomErrorResponse> customValidation(Exception e) {

		CustomErrorResponse errors = new CustomErrorResponse();

		errors.setTimestamp(LocalDateTime.now());
		errors.setError(e.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setOwner(ISRAEL_DUARTE);

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SearchException.class)
	public ResponseEntity<CustomErrorResponse> customSearch(Exception e) {

		CustomErrorResponse errors = new CustomErrorResponse();

		errors.setTimestamp(LocalDateTime.now());
		errors.setError(e.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setOwner(ISRAEL_DUARTE);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> customCityNotFound(Exception e) {

		CustomErrorResponse errors = new CustomErrorResponse();

		errors.setTimestamp(LocalDateTime.now());
		errors.setError(e.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setOwner(ISRAEL_DUARTE);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<CustomErrorResponse> customService(Exception e) {

		CustomErrorResponse errors = new CustomErrorResponse();

		errors.setTimestamp(LocalDateTime.now());
		errors.setError(e.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setOwner(ISRAEL_DUARTE);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}
}
