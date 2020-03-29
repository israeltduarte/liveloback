package br.com.iser.liveloback.service;

import java.util.List;
import org.springframework.util.MultiValueMap;
import br.com.iser.liveloback.model.City;
import br.com.iser.liveloback.model.dto.CityDTO;

public interface CityService {

    public City add(CityDTO cityDTO);

    public List<CityDTO> getByFilter(MultiValueMap<String, String> fields);

    public List<City> getAll();
}
