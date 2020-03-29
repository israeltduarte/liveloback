package br.com.iser.liveloback.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.iser.liveloback.model.Client;
import br.com.iser.liveloback.model.dto.ClientDTO;
import br.com.iser.liveloback.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {

        List<Client> allClients = clientService.getAll();

        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody ClientDTO clientDTO) {

        Client createdClient = clientService.add(clientDTO);

        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable(value = "idClient") final Integer id) {

        ClientDTO clientDTO = clientService.getById(id);

        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ClientDTO>> getClientByFilter(@RequestParam final String name, @PageableDefault(page = 0, size = 10) Pageable page) {

        Page<ClientDTO> response = clientService.getByFilter(name, page);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<ClientDTO> updateClientName(@RequestBody ClientDTO clientDTO, @PathVariable(value = "idClient") final Integer id) {

        ClientDTO response = clientService.updateName(id, clientDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{idClient}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable final Integer idClient) {

        Boolean response = clientService.delete(idClient);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
