package br.com.iser.liveloback.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.iser.liveloback.model.City;
import br.com.iser.liveloback.model.dto.CityDTO;
import br.com.iser.liveloback.service.CityService;

@RestController
@RequestMapping("/api/cities")
public class CityResource {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getAll() {

        List<City> allCities = cityService.getAll();

        return new ResponseEntity<>(allCities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody CityDTO cityDTO) {

        City createdCity = cityService.add(cityDTO);

        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CityDTO>> getCityByFilterName(@RequestParam MultiValueMap<String, String> fields) {

        List<CityDTO> cities = cityService.getByFilter(fields);

        return ResponseEntity.ok(cities);
    }
}
