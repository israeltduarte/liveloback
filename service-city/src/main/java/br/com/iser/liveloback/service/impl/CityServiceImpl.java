package br.com.iser.liveloback.service.impl;

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

   private static final String STATE = "state";
   private static final String NAME = "name";

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

      if (fields.get(NAME) == null) {
         listCitiesOpt = cityRepository.findByStateContaining(fields.get(STATE).get(0).toString());
      } else if (fields.get(STATE) == null) {
         listCitiesOpt = cityRepository.findByNameContaining(fields.get(NAME).get(0).toString());
      } else {
         listCitiesOpt =
               cityRepository.findByNameContainingAndStateContaining(fields.get(NAME).get(0).toString(), fields.get(STATE).get(0).toString());
      }

      if (!listCitiesOpt.isPresent() || listCitiesOpt.get().isEmpty()) {
         throw new CityNotFoundException(Message.CITY_NOT_FOUND);
      }

      return listCitiesOpt.get();
   }

   @Override
   public List<City> getAll() {

      return cityRepository.findAll();
   }

}
