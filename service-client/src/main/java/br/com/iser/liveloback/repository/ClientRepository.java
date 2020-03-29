package br.com.iser.liveloback.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.iser.liveloback.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByName(String name);

    Optional<List<Client>> findByNameContaining(String name);
}
