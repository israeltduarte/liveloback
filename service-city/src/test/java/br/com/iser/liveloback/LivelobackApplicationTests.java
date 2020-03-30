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
import org.junit.BeforeClass;
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
import br.com.iser.liveloback.util.CityConverter;
import br.com.iser.liveloback.validation.Validator;
import br.com.iser.liveloback.validation.exception.CityNotFoundException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class LivelobackApplicationTests {

   @Mock
   private CityRepository cityRepository;

   @Mock
   private CityServiceImpl cityService;

   @Mock
   private Validator validator;

   @BeforeClass
   public static void setUp() {
      FixtureFactoryLoader.loadTemplates("br.com.iser.liveloback.template");
   }

   @Before
   public void setupMock() {
      cityService = new CityServiceImpl();
      cityService.setValidator(validator);
      cityService.setCityRepository(cityRepository);
   }

   @Test
   public void testGetAll() {

      when(cityRepository.findAll()).thenReturn(Fixture.from(City.class).gimme(3, TestConstant.CITY_ENTITY));

      List<City> cities = cityService.getAll();
      assertNotNull(cities);
      assertEquals(3, cities.size());

      verify(cityRepository, times(1)).findAll();
   }

   @Test
   public void testAdd() {
      CityDTO dto = Fixture.from(CityDTO.class).gimme(TestConstant.CITY_DTO);
      City city = CityConverter.convertToCity(dto);

      when(cityRepository.save(city)).thenReturn(city);

      City savedCity = cityService.add(dto);

      assertNotNull(savedCity);

      verify(validator, times(1)).validateCityDTO(dto);
      verify(cityRepository, times(1)).save(city);
   }

   @Test
   public void testGetByFilter() {

      when(cityRepository.findByStateContaining("RS")).thenReturn(Optional.ofNullable(Fixture.from(City.class).gimme(2, TestConstant.CITY_ENTITY)));

      MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
      fields.add("state", "RS");

      List<CityDTO> cities = cityService.getByFilter(fields);
      assertNotNull(cities);
      assertEquals(2, cities.size());
   }

   @Test(expected = CityNotFoundException.class)
   public void testGetByFilterCityNotFoundException() {

      when(cityRepository.findByStateContaining("RS")).thenReturn(Optional.ofNullable(new ArrayList<>()));

      MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
      fields.add("state", "RS");

      cityService.getByFilter(fields);
   }
}
