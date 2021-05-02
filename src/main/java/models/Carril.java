package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Carril {
    private Integer numeroCarril;
    private Carro carro;
}
