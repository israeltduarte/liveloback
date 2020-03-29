package br.com.iser.liveloback.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import br.com.iser.liveloback.model.Client;
import br.com.iser.liveloback.model.dto.ClientDTO;

public class ClientConverter {

    public static Client convertToClient(ClientDTO source) {

        Client target = new Client();

        BeanUtils.copyProperties(source, target);

        return target;
    }

    public static ClientDTO convertToClientDTO(Client source) {

        ClientDTO target = new ClientDTO();

        BeanUtils.copyProperties(source, target);
        target.setLivingCity(source.getCity().getName());

        return target;
    }

    public static List<ClientDTO> convertToListClientDTO(List<Client> source) {

        List<ClientDTO> listDTO = new ArrayList<>();
        source.forEach(client -> {
            listDTO.add(convertToClientDTO(client));
        });

        return listDTO;
    }
}
