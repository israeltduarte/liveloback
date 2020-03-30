package br.com.iser.liveloback.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import br.com.iser.liveloback.model.City;
import br.com.iser.liveloback.model.dto.CityDTO;

public class CityConverter {

   public static City convertToCity(CityDTO source) {

      City target = new City();

      BeanUtils.copyProperties(source, target);

      return target;
   }

   private static CityDTO convertToCityDTO(City source) {

      CityDTO target = new CityDTO();

      BeanUtils.copyProperties(source, target);

      return target;
   }

   public static List<CityDTO> convertToListCityDTO(List<City> source) {

      List<CityDTO> listDTO = new ArrayList<>();
      source.forEach(client -> {
         listDTO.add(convertToCityDTO(client));
      });

      return listDTO;
   }
}
