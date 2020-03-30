package br.com.iser.liveloback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.iser.liveloback.client.CityClient;
import br.com.iser.liveloback.model.City;
import br.com.iser.liveloback.model.Client;
import br.com.iser.liveloback.model.dto.ClientDTO;
import br.com.iser.liveloback.repository.ClientRepository;
import br.com.iser.liveloback.service.ClientService;
import br.com.iser.liveloback.util.ClientConverter;
import br.com.iser.liveloback.util.Message;
import br.com.iser.liveloback.validation.Validator;
import br.com.iser.liveloback.validation.exception.CityNotFoundException;
import br.com.iser.liveloback.validation.exception.ClientNotFoundException;
import br.com.iser.liveloback.validation.exception.ServiceException;
import br.com.iser.liveloback.validation.exception.ValidationException;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private CityClient cityClient;

	@Autowired
	private Validator validator;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client add(ClientDTO clientDTO) {

		validator.validateClientDTO(clientDTO);

		Client client = ClientConverter.convertToClient(clientDTO);
		City city = configureCity(clientDTO);
		client.setCity(city);

		return clientRepository.save(client);
	}

	private City configureCity(ClientDTO clientDTO) {

		MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
		fields.add("name", clientDTO.getLivingCity());

		List<City> cities = new ArrayList<>();

		try {
			cities = cityClient.getByFilter(fields).getBody();
		} catch (Exception e) {
			throw new CityNotFoundException(Message.CITY_NOT_FOUND);
		}

		return checkCity(cities);
	}

	private City checkCity(List<City> cities) {
		if (cities.size() > 1) {
			throw new ValidationException(Message.MORE_THAN_ONE_CITY_FOUND);
		}

		return cities.get(0);
	}

	@Override
	public ClientDTO getById(Integer id) {

		validator.validateId(id);

		Optional<Client> clientOptional = clientRepository.findById(id);
		if (!clientOptional.isPresent()) {
			throw new ClientNotFoundException(Message.CLIENT_NOT_FOUND);
		}

		return ClientConverter.convertToClientDTO(clientOptional.get());
	}

	@Override
	public PageImpl<ClientDTO> getByFilter(String name, Pageable page) {

		validator.validateName(name);

		List<Client> listClients;

		Optional<List<Client>> listClientsOpt = clientRepository.findByNameContaining(name);
		if (!listClientsOpt.isPresent() || listClientsOpt.get().isEmpty()) {
			throw new ClientNotFoundException(Message.CLIENT_NOT_FOUND);
		}

		listClients = listClientsOpt.get();

		return new PageImpl<>(ClientConverter.convertToListClientDTO(listClients), page, listClients.size());
	}

	@Override
	public ClientDTO updateName(Integer id, ClientDTO clientDTO) {

		validator.validateClientName(clientDTO);
		validator.validateId(id);

		Optional<Client> clientOpt = clientRepository.findById(id);
		if (!clientOpt.isPresent()) {
			throw new ClientNotFoundException(Message.CLIENT_NOT_FOUND);
		}

		Client client = clientOpt.get();
		client.setName(clientDTO.getName());

		return ClientConverter.convertToClientDTO(clientRepository.save(client));
	}

	@Override
	public Boolean delete(Integer id) {

		validator.validateId(id);

		Optional<Client> clientOptional = clientRepository.findById(id);
		if (!clientOptional.isPresent()) {
			throw new ClientNotFoundException(Message.CLIENT_NOT_FOUND_UNABLE_TO_DELETE);
		}

		clientRepository.delete(clientOptional.get());

		return true;
	}

	@Override
	public List<Client> getAll() {

		return clientRepository.findAll();
	}
}
