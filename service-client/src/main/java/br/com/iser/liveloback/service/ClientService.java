package br.com.iser.liveloback.service;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import br.com.iser.liveloback.model.Client;
import br.com.iser.liveloback.model.dto.ClientDTO;

public interface ClientService {

    public Client add(ClientDTO clientDTO);

    public ClientDTO getById(Integer id);

    public PageImpl<ClientDTO> getByFilter(String name, Pageable page);

    public ClientDTO updateName(Integer id, ClientDTO clientDTO);

    public Boolean delete(Integer id);

    public List<Client> getAll();
}
