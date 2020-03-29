package br.com.iser.liveloback.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.iser.liveloback.model.City;

@FeignClient(url = "localhost:8080/api/cities", name = "city")
public interface CityClient {

    @GetMapping("/filter")
    ResponseEntity<List<City>> getByFilter(@RequestParam MultiValueMap<String, String> fields);
}
