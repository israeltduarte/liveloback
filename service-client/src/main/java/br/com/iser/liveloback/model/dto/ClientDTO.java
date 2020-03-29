package br.com.iser.liveloback.model.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ClientDTO {

    private String name;
    private Character gender;
    private LocalDate birthdate;
    private Integer age;
    private String livingCity;
}
