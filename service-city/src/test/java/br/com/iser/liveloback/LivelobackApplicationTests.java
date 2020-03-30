package br.com.iser.liveloback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import br.com.iser.liveloback.constant.TestConstant;
import br.com.iser.liveloback.model.City;
import br.com.iser.liveloback.model.dto.CityDTO;
import br.com.iser.liveloback.repository.CityRepository;
import br.com.iser.liveloback.service.impl.CityServiceImpl;
import br.com.iser.liveloback.validation.Validator;
import br.com.iser.liveloback.validation.exception.CityNotFoundException;
import br.com.iser.liveloback.validation.exception.SearchException;
import br.com.iser.liveloback.validation.exception.ValidationException;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(MockitoJUnitRunner.class)
class LivelobackApplicationTests {

   @Mock
   private CityRepository cityRepository;

   @Mock
   private CityServiceImpl cityService;

   @Mock
   private Validator validator;

   @Before
   public void setupMock() {
      cityService = new CityServiceImpl();
      cityService.setValidator(validator);
      cityService.setCityRepository(cityRepository);
   }

   @Test
   void testGetAll() {

      when(cityRepository.findAll()).thenReturn(Fixture.from(City.class).gimme(3, TestConstant.CITY_ENTITY));

      List<City> cities = cityService.getAll();
      assertNotNull(cities);
      assertEquals(5, cities.size());

      verify(cityRepository, times(1)).findAll();
   }

   @Test
   void testAdd() {

      City city = Fixture.from(City.class).gimme(TestConstant.CITY_ENTITY);
      when(cityRepository.save(city)).thenReturn(city);

      CityDTO cityDTO = Fixture.from(CityDTO.class).gimme(TestConstant.CITY_DTO);

      City savedCity = cityService.add(cityDTO);
      assertNotNull(savedCity);

      verify(validator, times(1)).validateCityDTO(cityDTO);
      verify(cityRepository, times(1)).save(city);
   }

   @Test(expected = ValidationException.class)
   void testAddValidationMissingName() {

      City city = Fixture.from(City.class).gimme(TestConstant.CITY_ENTITY);
      when(cityRepository.save(city)).thenReturn(city);

      CityDTO cityDTO = Fixture.from(CityDTO.class).gimme(TestConstant.CITY_DTO);
      cityDTO.setName(null);

      City savedCity = cityService.add(cityDTO);
   }

   @Test(expected = ValidationException.class)
   void testAddValidationMissingDTO() {

      City city = Fixture.from(City.class).gimme(TestConstant.CITY_ENTITY);
      when(cityRepository.save(city)).thenReturn(city);

      CityDTO cityDTO = Fixture.from(CityDTO.class).gimme(TestConstant.CITY_DTO);
      cityDTO = null;

      City savedCity = cityService.add(cityDTO);
   }

   @Test(expected = ValidationException.class)
   void testAddValidationMissingState() {

      City city = Fixture.from(City.class).gimme(TestConstant.CITY_ENTITY);
      when(cityRepository.save(city)).thenReturn(city);

      CityDTO cityDTO = Fixture.from(CityDTO.class).gimme(TestConstant.CITY_DTO);
      cityDTO.setState(null);

      City savedCity = cityService.add(cityDTO);
   }

   @Test
   void testGetByFilter() {

      when(cityRepository.findByStateContaining("RS")).thenReturn(Optional.ofNullable(Fixture.from(City.class).gimme(2, TestConstant.CITY_ENTITY)));

      MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
      fields.add("state", "RS");

      List<CityDTO> cities = cityService.getByFilter(fields);
      assertNotNull(cities);
      assertEquals(2, cities.size());
   }

   @Test(expected = SearchException.class)
   void testGetByFilterSearchException() {

      when(cityRepository.findByStateContaining("RS")).thenReturn(Optional.ofNullable(Fixture.from(City.class).gimme(2, TestConstant.CITY_ENTITY)));

      MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
      fields.add("state", "RS");
      fields.add("name", null);

      cityService.getByFilter(fields);
   }

   @Test(expected = CityNotFoundException.class)
   void testGetByFilterCityNotFoundException() {

      when(cityRepository.findByStateContaining("RS")).thenReturn(Optional.ofNullable(new ArrayList<>()));

      MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
      fields.add("state", "RS");

      cityService.getByFilter(fields);
   }
}
