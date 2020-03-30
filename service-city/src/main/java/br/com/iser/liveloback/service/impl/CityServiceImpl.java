package br.com.iser.liveloback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import br.com.iser.liveloback.model.City;
import br.com.iser.liveloback.model.dto.CityDTO;
import br.com.iser.liveloback.repository.CityRepository;
import br.com.iser.liveloback.service.CityService;
import br.com.iser.liveloback.util.CityConverter;
import br.com.iser.liveloback.util.Message;
import br.com.iser.liveloback.validation.Validator;
import br.com.iser.liveloback.validation.exception.CityNotFoundException;
import lombok.Setter;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	@Setter
	private Validator validator;

	@Autowired
	@Setter
	private CityRepository cityRepository;

	@Override
	public City add(CityDTO cityDTO) {

		validator.validateCityDTO(cityDTO);

		City city = CityConverter.convertToCity(cityDTO);

		return cityRepository.save(city);
	}

	@Override
	public List<CityDTO> getByFilter(MultiValueMap<String, String> params) {

		validator.validateCitySearch(params);

		List<City> listCities = search(params);

		return CityConverter.convertToListCityDTO(listCities);
	}

	private List<City> search(MultiValueMap<String, String> fields) {

		Optional<List<City>> listCitiesOpt;
		List<City> listCities = new ArrayList<>();

		if (fields.get("name") == null) {
			listCitiesOpt = cityRepository.findByStateContaining(fields.get("state").get(0).toString());
		} else if (fields.get("state") == null) {
			listCitiesOpt = cityRepository.findByNameContaining(fields.get("name").get(0).toString());
		} else {
			listCitiesOpt = cityRepository.findByNameContainingAndStateContaining(fields.get("name").get(0).toString(),
					fields.get("state").get(0).toString());
		}

		if (listCitiesOpt.isPresent()) {
			listCities = listCitiesOpt.get();
			if (listCities.isEmpty()) {
				throw new CityNotFoundException(Message.CITY_NOT_FOUND);
			}
		}

		return listCities;
	}

	@Override
	public List<City> getAll() {

		return cityRepository.findAll();
	}

}
