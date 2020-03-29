package br.com.iser.liveloback.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import br.com.iser.liveloback.model.dto.CityDTO;
import br.com.iser.liveloback.model.dto.ClientDTO;
import br.com.iser.liveloback.util.Message;
import br.com.iser.liveloback.validation.exception.SearchException;
import br.com.iser.liveloback.validation.exception.ValidationException;

@Component
public class Validator {

	public void validateClientDTO(ClientDTO clientDTO) {

		if (clientDTO == null) {
			throw new ValidationException(Message.INVALID_DATA);
		}

		if (clientDTO.getLivingCity() == null) {
			throw new ValidationException(Message.MISSING_LIVING_CITY);
		}

		if (clientDTO.getName() == null) {
			throw new ValidationException(Message.MISSING_NAME);
		}
	}

	public void validateId(Integer id) {

		if (id == null) {
			throw new ValidationException(Message.INVALID_ID);
		}
	}

	public void validateClientName(ClientDTO clientDTO) {

		if (clientDTO.getName() == null) {
			throw new ValidationException(Message.MISSING_NAME);
		}
	}

	public void validateName(String name) {

		if (name == null) {
			throw new ValidationException(Message.MISSING_NAME);
		}
	}

	public void validateCityDTO(CityDTO cityDTO) {

		if (cityDTO == null || cityDTO.getName() == null || cityDTO.getState() == null) {
			throw new ValidationException(Message.INVALID_DATA);
		}
	}

	public void validateState(String state) {

		if (state == null) {
			throw new ValidationException(Message.MISSING_STATE);
		}
	}

	public void validateCitySearch(MultiValueMap<String, String> fields) {

		if (fields == null || (fields.get("name") == null && fields.get("state") == null)) {
			throw new SearchException(Message.INVALID_DATA);
		}
	}
}
