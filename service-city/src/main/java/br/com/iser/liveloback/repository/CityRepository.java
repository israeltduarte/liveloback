package br.com.iser.liveloback.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.iser.liveloback.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<List<City>> findByNameContaining(String name);

    Optional<List<City>> findByStateContaining(String state);

    Optional<List<City>> findByNameContainingAndStateContaining(String string, String string2);
}
